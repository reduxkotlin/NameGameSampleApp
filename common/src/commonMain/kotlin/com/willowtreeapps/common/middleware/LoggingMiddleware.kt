package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Logger

fun loggerMiddleware(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
    val result = nextDispatcher(action)
    Logger.d("DISPATCH action: ${action::class.simpleName}")
    return result
}
