package com.willowtreeapps.common.middleware

import org.reduxkotlin.GetState
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Logger

fun loggerMiddleware(getState: GetState<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
    val result = nextDispatcher(action)
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    return result
}
