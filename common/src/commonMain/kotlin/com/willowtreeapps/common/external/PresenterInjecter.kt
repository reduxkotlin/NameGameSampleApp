package com.willowtreeapps.common.external

import com.willowtreeapps.common.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.reduxkotlin.*
import kotlin.coroutines.CoroutineContext

data class AttachView(val view: View)
data class DetachView(val view: View)
data class ClearView(val view: View)

/*
 * Dispatch set by PresenterMiddleware that is same function as the store's dispatch.
 * It is globally accessible and value is set when createStore is called.
 */
lateinit var rootDispatch: Dispatcher

/*
 * All views implement this interface.
 */
interface View


interface PresenterProvider<State> {
    fun presenter(): Presenter<View, State> = throw NotImplementedError("Must implement this method to provide a presenterBuilder for ${this::class}")
}

interface ViewWithProvider<State>: View, PresenterProvider<State>

enum class ViewLifecycle {
    ATTACHED,
    DETACHED
}


data class StoreSubscriberHolder(val lifecycleState: ViewLifecycle, val subscriber: StoreSubscriber)

/*
fun <State> presenterEnhancer(): StoreEnhancer<State> = { storeCreator: StoreCreator<State> ->
    { reducer: Reducer<State>, initialState: State, en: Any? ->
        val store = storeCreator(reducer, initialState, en)
        rootDispatch = store.dispatch
        store
    }
}

 */

fun <State> presenterEnhancer(uiContext: CoroutineContext): StoreEnhancer<State> = { storeCreator: StoreCreator<State> ->
    { reducer: Reducer<State>, initialState: State, en: Any? ->

        val enhancers = if (en == null) {
            presenterEnhancer(uiContext)
        } else {
            compose(listOf(presenterEnhancer(uiContext), en as StoreEnhancer<State>))
        }
        val store = storeCreator(reducer, initialState, enhancers)
        store.dispatch = presenterMiddleware<State>(uiContext)(store)(store.dispatch)
        rootDispatch = store.dispatch
        store
    }
}

/**
 * PresenterMiddleware that attaches presenters with views and calls subscribers (Presenters)
 * to update the view when state changes.
 * Each view must attach/detach itself as it becomes visible/not visible by dispatching AttachView or DetachView
 * Attaching sets the presenter to the view.
 * PresenterFactory subscribes to changes in state, and passes state to presenters.
 */
private fun <State> presenterMiddleware(uiContext: CoroutineContext): Middleware<State> = { store ->

    val uiScope = CoroutineScope(uiContext)
    val subscribers = mutableMapOf<ViewWithProvider<State>, StoreSubscriberHolder>()
    var subscription: StoreSubscription? = null
    val coroutineScope = CoroutineScope(uiContext)

    fun hasAttachedViews() = subscribers.isNotEmpty()

    fun onStateChange() {
        coroutineScope.launch {
            subscribers.forEach {
                if (it.value.lifecycleState == ViewLifecycle.ATTACHED) {
                    it.value.subscriber()
                }
            }
        }
    }

    fun attachView(view: ViewWithProvider<State>) {
        Logger.d("AttachView: $view", Logger.Category.LIFECYCLE)
        //TODO is hanging onto subscription needed?
        if (subscription == null) {
            subscription = store.subscribe(::onStateChange)
        }
        if (subscribers.containsKey(view) && subscribers[view]!!.lifecycleState == ViewLifecycle.DETACHED) {
            //view is reattached and does not need updating unless state has changed
            val subscriber = subscribers[view]!!.subscriber
            subscribers[view] = StoreSubscriberHolder(ViewLifecycle.ATTACHED, subscriber)
            subscriber()
        } else {
            val subscriber = view.presenter()(view, uiScope)(store)
            //call subscriber to trigger initial view update
            subscriber()
            subscribers[view] = StoreSubscriberHolder(ViewLifecycle.ATTACHED, subscriber)
        }
    }

    fun detachView(view: ViewWithProvider<State>) {
        Logger.d("DetachView: $view", Logger.Category.LIFECYCLE)
        subscribers[view] = StoreSubscriberHolder(ViewLifecycle.DETACHED, subscribers[view]!!.subscriber)
    }

    fun clearView(view: ViewWithProvider<State>) {
        Logger.d("ClearView: $view", Logger.Category.LIFECYCLE)
        subscribers.remove(view)

        if (!hasAttachedViews()) {
            subscription?.invoke()
            subscription = null
        }
    }

    { next: Dispatcher ->
        { action: Any ->
            when (action) {
                is AttachView -> attachView(action.view as ViewWithProvider<State>)
                is DetachView -> detachView(action.view as ViewWithProvider<State>)
                is ClearView -> clearView(action.view as ViewWithProvider<State>)
                else -> next(action)
            }
        }
    }
}


/**
 * @param View a view interface that will be passed to the presenter
 * @param CoroutineScope scope on which the reselect action will be executed.  Typically a UI scope.
 */
typealias Presenter<View, State> = (View, CoroutineScope) -> (Store<State>) -> StoreSubscriber

typealias PresenterBuilder<State, View> = ((View.() -> ((SelectorSubscriberBuilder<State>.() -> Unit))))

typealias PresenterBuilderWithViewArg<State, View> = ((View) -> (((SelectorSubscriberBuilder<State>.() -> Unit))))

/**
 * A convenience function for create a typed presenter builder for your App.
 *
 * usage:
 *        fun <V : LibraryView> presenter(actions: PresenterBuilder<AppState, V>): Presenter<View<AppState>> {
 *             return createGenericPresenter(actions) as Presenter<View<AppState>>
 *        }
 *
 *        val myPresenter = presenter<MyView> {{
 *            select { state.title } then { updateTitle(state.title) }
 *        }}
 *
 * @param actions - a PresenterBuilder describing actions to be taken on state changes.
 * @return a Presenter function
 *
 */
fun <State : Any, V : View> createGenericPresenter(actions: PresenterBuilder<State, V>): Presenter<V, State> {
    return { view: V, coroutineScope ->
        { store: Store<State> ->
            val actions2 = actions(view)
            val sel = selectorSubscriberFn(store, view, actions2)
            sel
        }
    }
}

/**
 * Helper function that creates a DSL for subscribing to changes in specific state fields and actions to take.
 * Inside the lambda there is access to the current state through the var `state`
 *
 * ex:
 *      val sel = selectorSubscriberFn {
 *          withSingleField({it.foo}, { actionWhenFooChanges() }
 *
 *          withAnyChange {
 *              //called whenever any change happens to state
 *              view.setMessage(state.barMsg) //state is current state
 *          }
 *      }
 */
val selectorSubscriberMap: MutableMap<Any, SelectorSubscriberBuilder<*>> = mutableMapOf()

fun <State : Any, V : Any> selectorSubscriberFn(store: Store<State>, view: V, selectorSubscriberBuilderInit: SelectorSubscriberBuilder<State>.() -> Unit): StoreSubscriber {
    val subscriberBuilder: SelectorSubscriberBuilder<State> = SelectorSubscriberBuilder(store)
    subscriberBuilder.selectorSubscriberBuilderInit()
    selectorSubscriberMap[view] = subscriberBuilder
    return {
        selectorSubscriberMap[view]!!.selectorList.forEach { entry ->
            (entry.key as Selector<State, *>).onChangeIn(store.getState() as State) { entry.value(store.getState()) }
        }
        selectorSubscriberMap[view]!!.withAnyChangeFun?.invoke()
    }
}
