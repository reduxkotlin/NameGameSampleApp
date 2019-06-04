package com.willowtreeapps.common.middleware

import org.reduxkotlin.GetState
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Logger
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Middleware
import org.reduxkotlin.Store

fun loggerMiddleware(getState: GetState, nextDispatcher: (Any) -> Any, action: Any): Any {
    val result = nextDispatcher(action)
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    return result
}

val loggerMiddleware2: Middleware =
        { store ->
            { next ->
                { action ->
                    {
                        val result = next(action)
                        Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
                        result
                    }
                }
            }
        }

val loggerMiddleware3 = middleWare { store, next, action ->
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    next(action)
}

fun middleWare(dispatch: (Store, Dispatcher, Any) -> Any): Middleware =
        { store ->
            { next ->
                { action: Any ->
                    {
                        dispatch(store, next, action)
                    }
                }
            }
        }
