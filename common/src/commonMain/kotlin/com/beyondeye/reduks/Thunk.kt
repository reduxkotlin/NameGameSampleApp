package com.beyondeye.reduks

/**
 * single method interface, mainly used because kotlin does not support yet type alias for function types
 * Created by daely on 5/24/2016.
 */
//TODO find way to check is action is instance of Thunk type alias in ThunkMiddleware
//typealias Thunk = (((Any) -> Any), state: Any) -> Any
interface Thunk<S> : Action {
    fun execute(dispatcher: (Any)->Any, state: S): Any
}
