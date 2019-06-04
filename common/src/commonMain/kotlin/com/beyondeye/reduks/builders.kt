package com.beyondeye.reduks

import org.reduxkotlin.Store
import org.reduxkotlin.StoreSubscriber

class SelectorSubscriberBuilder<S : Any>(val store: Store) {
    val state: S
        get() = store.getState() as S

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
fun <S : Any> SelectorSubscriberFn(store: Store, selectorSubscriberBuilderInit: SelectorSubscriberBuilder<S>.() -> Unit): StoreSubscriber {
    val subBuilder = SelectorSubscriberBuilder<S>(store)
    subBuilder.selectorSubscriberBuilderInit()
    return {
        subBuilder.selectorList.forEach { entry ->
            entry.key.onChangeIn(store.getState() as S) { entry.value(store.getState()) }
        }
        subBuilder.withAnyChangeFun?.invoke()
    }
}
