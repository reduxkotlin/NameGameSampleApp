package org.reduxkotlin.namegame.common.middleware

import org.reduxkotlin.namegame.common.Actions
import org.reduxkotlin.namegame.common.AppState
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
