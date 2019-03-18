package com.willowtreeapps.common

import com.beyondeye.reduks.SimpleStore
import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware
import com.willowtreeapps.common.middleware.NavigationMiddleware
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.ViewEffectsMiddleware
import com.willowtreeapps.common.repo.Profile

data class AppState(val isLoadingProfiles: Boolean,
                    val profiles: List<Profile> = listOf(),
                    val errorLoadingProfiles: Boolean = false,
                    val errorMsg: String = "") {
    companion object {
        val INITIAL_STATE = AppState(false)
    }
}

class Game(navigator: Navigator) {
    val navigationMiddleware = NavigationMiddleware(navigator)
    val viewEffectsMiddleware = ViewEffectsMiddleware()
    val appStore by lazy {
        SimpleStore(AppState.INITIAL_STATE, reducer)
                .applyMiddleware(::thunkMiddleware,
                        viewEffectsMiddleware::dispatch,
                        navigationMiddleware::dispatch)
    }
}

