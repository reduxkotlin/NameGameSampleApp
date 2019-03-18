package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState

class NavigationMiddleware(val navigator: Navigator) {

    fun dispatch(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        val result = nextDispatcher(action)
        when (action) {
            is Actions.FetchingProfilesSuccessAction -> navigator.goto(Screen.QUESTION)
        }
        return result
    }


}

enum class Screen {
    START,
    QUESTION,
    ROUND_COMPLETE
}

interface Navigator {
    fun goto(screen: Screen)
}