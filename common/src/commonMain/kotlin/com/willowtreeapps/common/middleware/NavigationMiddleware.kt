package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.Actions
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Store

internal class NavigationMiddleware(private val navigator: Navigator) {

    fun dispatch(store: Store) = { next: Dispatcher ->
        { action: Any ->
            when (action) {
                is Actions.FetchingItemsSuccessAction -> navigator.goto(Screen.QUESTION)
                is Actions.GameCompleteAction -> navigator.goto(Screen.GAME_COMPLETE)
                is Actions.StartOverAction -> navigator.goto(Screen.START)
                is Actions.SettingsTappedAction -> navigator.goto(Screen.SETTINGS)
            }
            next(action)
        }
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
