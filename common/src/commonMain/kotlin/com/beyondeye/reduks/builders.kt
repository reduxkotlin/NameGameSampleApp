package com.beyondeye.reduks

/**
 * builder methods for making the code more clear when defining reduks objects
 */

/**
 * see also https://github.com/reactjs/redux/blob/master/docs/Glossary.md#middleware
 */


class ThunkImpl<S>(val thunkFn: (dispatcher: (Any) -> Any, state: S) -> Any) : Thunk<S> {
    override fun execute(dispatcher: (Any) -> Any, state: S): Any = thunkFn(dispatcher, state)
}

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
        selectorList[sel] = action
    }
}

/**
 * Helper function that creates a DSL for subscribing to changes in specific state fields and actions to take.
 * Inside the lambda there is access to the current state through the var `state`
 *
 * ex:
 *      SelectorSubscriberFn {
 *          withSingleField({it.foo}, { actionWhenFooChanges() }
 *
 *          withAnyChange {
 *              //called whenever any change happens to state
 *              view.setMessage(state.barMsg) //state is current state
 *          }
 *      }
 */
fun <S : Any> SelectorSubscriberFn(store: Store<S>, selectorSubscriberBuilderInit: SelectorSubscriberBuilder<S>.() -> Unit): StoreSubscriber {
    val subBuilder = SelectorSubscriberBuilder(store)
    subBuilder.selectorSubscriberBuilderInit()
    return {
        subBuilder.selectorList.forEach { entry ->
            entry.key.onChangeIn(store.state) { entry.value(store.state) }
        }
        subBuilder.withAnyChangeFun?.invoke()
    }
}


fun <S> ThunkFn(thunkFn: (dispatcher: (Any) -> Any, state: S) -> Any) = ThunkImpl(thunkFn)
