package com.beyondeye.reduks

/**
 * builder methods for making the code more clear when defining reduks objects
 * Created by daely on 8/31/2016.
 */

/**
 * see also https://github.com/reactjs/redux/blob/master/docs/Glossary.md#middleware
 */


/**
 * get a store creator and return a new enhanced one
 * see https://github.com/reactjs/redux/blob/master/docs/Glossary.md#store-enhancer
 * Created by daely on 8/23/2016.
 */
class StoreEnhancerImpl<S>(val storeEnhancerFn:(next: StoreCreator<S>)-> StoreCreator<S>): StoreEnhancer<S> {
    override fun enhance(next: StoreCreator<S>): StoreCreator<S> = storeEnhancerFn(next)
}

class StoreSubscriberImpl<S>(val subscriberFn: ()->Unit) : StoreSubscriber<S>{
    override fun onStateChange(){subscriberFn()}
}

class StoreSubscriberBuilderImpl<S>(val storeSubscriberBuilderFn:(store: Store<S>)-> StoreSubscriber<S>): StoreSubscriberBuilder<S> {
    override fun build(store: Store<S>): StoreSubscriber<S> = storeSubscriberBuilderFn(store)
}
class StoreSubscriberBuilderImpl2<S:Any>(val storeSubscriberBuilderFn2:(store: Store<S>,selector:SelectorBuilder<S>)-> StoreSubscriber<S>): StoreSubscriberBuilder<S> {
    val selector=SelectorBuilder<S>()
    override fun build(store: Store<S>): StoreSubscriber<S> = storeSubscriberBuilderFn2(store,selector)
}

class ThunkImpl<S>(val thunkFn:(dispatcher: (Any)->Any, state: S)->Any) : Thunk<S> {
    override fun execute(dispatcher:  (Any)->Any, state: S): Any = thunkFn(dispatcher,state)
}


fun <S> MiddlewareFn(middlewareFn:(store: Store<S>, nextDispatcher:  (Any)->Any, action:Any)->Any) = middlewareFn
fun <S> ReducerFn(reducerFn:(state:S, action:Any)->S) = reducerFn
fun <S> StoreEnhancerFn(storeEnhancerFn:(next: StoreCreator<S>)-> StoreCreator<S>)= StoreEnhancerImpl(storeEnhancerFn)
fun <S> StoreSubscriberFn(subscriberFn: ()->Unit)= StoreSubscriberImpl<S>(subscriberFn)
fun <S> StoreSubscriberBuilderFn(storeSubscriberBuilderFn:(store: Store<S>)-> StoreSubscriber<S>) = StoreSubscriberBuilderImpl(storeSubscriberBuilderFn)
fun <S:Any> StoreSubscriberBuilderFn(storeSubscriberBuilderFn2:(store: Store<S>,selector:SelectorBuilder<S>)-> StoreSubscriber<S>) = StoreSubscriberBuilderImpl2(storeSubscriberBuilderFn2)
class SelectorSubscriberBuilder<S : Any>(val store: Store<S>) {
    val state: S
        get() = store.state

    var withAnyChangeFun: (() -> Unit)? = null

    fun withAnyChange(f: () -> Unit) {
        withAnyChangeFun = f
    }

    val selectorList = mutableMapOf<Selector<S, Any>, (Any) -> Unit>()
    fun withSingleField(selector: (S) -> Any, action: (Any) -> Unit) {
        val selBuilder = SelectorBuilder<S>()
        val sel = selBuilder.withSingleField(selector)
        selectorList.put(sel, action)
    }
}

/**
 * Helper function that creates a DSL for subscribing to changes in specific state fields and actions to take
 * ex:
 *      SelectorSubscriberFn {
 *          withSingleField({it.foo}, { actionWhenFooChanges() }
 *
 *          withAnyChange {
 *              //called whenever any change happens to state
 *          }
 *      }
 */
fun <S : Any> SelectorSubscriberFn(store: Store<S>, selectorSubscriberBuilderInit: SelectorSubscriberBuilder<S>.() -> Unit): StoreSubscriber<S> {
    val subBuilder = SelectorSubscriberBuilder(store)
    subBuilder.selectorSubscriberBuilderInit()
    return StoreSubscriberBuilderFn<S> { store ->
        StoreSubscriberFn {
            subBuilder.selectorList.forEach { entry ->
                entry.key.onChangeIn(store.state) { entry.value(store.state) }
            }
            subBuilder.withAnyChangeFun?.invoke()
        }
    }.build(store)
}


fun <S> ThunkFn(thunkFn:(dispatcher:  (Any)->Any, state: S)->Any) = ThunkImpl(thunkFn)
