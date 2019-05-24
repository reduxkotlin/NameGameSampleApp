package com.beyondeye.reduks

import com.willowtreeapps.common.Logger
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.GetState
import org.reduxkotlin.Middleware

typealias Thunk = (Dispatcher)->Any


fun <S : Any> createThunkMiddleware(): Middleware<S> {
    return { getState: GetState<S>, nextDispatcher: Dispatcher, action: Any ->
        if (action is Function<*>) {
            try {
                (action as Thunk)(nextDispatcher)
            } catch (e: Exception) {
                Logger.d("Dispatching functions must use type Thunk: " + e.message)
            }
        } else {
            nextDispatcher(action)
        }
    }
}
