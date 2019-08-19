package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Store
import org.reduxkotlin.middleware

internal fun navigationMiddleware(navigator: Navigator) = middleware<AppState> { store, next, action ->
    when (action) {
        is NavigationActions.NavigateTo -> navigator.goto(action.screen)
        is Actions.FetchingItemsSuccessAction -> navigator.goto(Screen.QUESTION)
    }
    next(action)
}

internal class NavigationActions {
    data class NavigateTo(val screen: Screen)
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
