package com.beyondeye.reduks


typealias dispatcherfn_ = (action: Any) -> Any

/**
 * class that hold a weak reference to a dispatcher function,
 * it is useful in android when we want to allow to dispatch actions
 * from objects that do not have direct access to the parent activity,
 * while avoiding potential memory leaks, and also encapsulating the
 * data type of a dispatcher function.
 */
class DispatcherFn(fn: dispatcherfn_) {
    val fn_: dispatcherfn_?

    init {
        fn_ = fn
    }

    val fn: dispatcherfn_?
        get() {
            return fn_
        }

    fun dispatch(action: Any): Any? = fn?.invoke(action)
    operator fun invoke(action: Any): Any? = fn?.invoke(action)
}