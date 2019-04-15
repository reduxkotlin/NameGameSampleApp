package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState

internal class NavigationMiddleware(private val navigator: Navigator) {

    fun dispatch(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        val result = nextDispatcher(action)
        when (action) {
            is Actions.FetchingItemsSuccessAction -> navigator.goto(Screen.QUESTION)
            is Actions.GameCompleteAction -> navigator.goto(Screen.GAME_COMPLETE)
            is Actions.StartOverAction -> navigator.goto(Screen.START)
            is Actions.SettingsTappedAction -> navigator.goto(Screen.SETTINGS)
        }
        return result
    }
}

enum class Screen {
    START,
    QUESTION,
    GAME_COMPLETE,
    SETTINGS
}

interface Navigator {
    fun goto(screen: Screen)
}