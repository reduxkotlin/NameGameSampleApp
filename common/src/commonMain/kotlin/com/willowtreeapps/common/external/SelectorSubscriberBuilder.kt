package com.willowtreeapps.common.external

import org.reduxkotlin.Store


/**
 * A Selector Subscriber - group of selectors that subscribe to store state changes.
 *
 * @param State is the type of the state object returned by the store.
 * @property store The redux store
 * @constructor creates an empty SelectorSubscriberBuilder
 */
class SelectorSubscriberBuilder<State : Any>(val store: Store<State>) {

    val selectorList = mutableMapOf<Selector<State, Any>, (Any) -> Unit>()

    //state is here to make available to lambda with receiver in DSL
    val state: State
        get() = store.getState() as State

    var withAnyChangeFun: (() -> Unit)? = null

    fun withAnyChange(f: () -> Unit) {
        withAnyChangeFun = f
    }


    fun withSingleField(selector: (State) -> Any, action: (Any) -> Unit) {
        val selBuilder = SelectorBuilder<State>()
        val sel = selBuilder.withSingleField(selector)
        selectorList[sel] = action
    }

    infix fun SelectorSubscriberBuilder<State>.select(selector: (State) -> Any): AbstractSelector<State, Any> =
            SelectorBuilder<State>().withSingleField(selector)

    infix fun SelectorSubscriberBuilder<State>.on(selector: (State) -> Any): AbstractSelector<State, Any> =
            SelectorBuilder<State>().withSingleField(selector)

    operator fun (() -> Any).unaryPlus(): AbstractSelector<State, Any> {
        val that = this
        return SelectorBuilder<State>().withSingleField { that() }
    }

    infix fun AbstractSelector<State, Any>.then(action: (Any) -> Unit) {
        selectorList[this] = action
    }

    infix operator fun AbstractSelector<State, Any>.plus(action: (Any) -> Unit) {
        selectorList[this] = action
    }
}


