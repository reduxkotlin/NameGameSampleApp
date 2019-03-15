package com.willowtreeapps.common

import com.beyondeye.reduks.SimpleStore
import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware
import com.willowtreeapps.common.middleware.ViewEffectsMiddleware

data class AppState(val name: String) {
    companion object {
        val INITIAL_STATE = AppState("placeholder")
    }
}


val viewEffectsMiddleware = ViewEffectsMiddleware()
val appStore by lazy {
    SimpleStore(AppState.INITIAL_STATE, reducer)
            .applyMiddleware(::thunkMiddleware, viewEffectsMiddleware::dispatch)
}