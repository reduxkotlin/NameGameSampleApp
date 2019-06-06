package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Logger
import org.reduxkotlin.*

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

fun logMiddleware(store: Store) = { next: Dispatcher ->
    { action: Any ->
        next(action)
    }
}


val loggerMiddleware3 = middleware { store, next, action ->
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    next(action)
}
