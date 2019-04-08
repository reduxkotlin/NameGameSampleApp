package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.Actions.ChangeNumQuestionsSettingsAction
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.repo.LocalStorageSettingsRepository

/**
 * Save and Loads user settings from local storage
 */
class SettingsMiddleware(private val settings: LocalStorageSettingsRepository) {

    fun dispatch(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        when (action) {
            is ChangeNumQuestionsSettingsAction -> settings.saveNumRounds(action.num)

            is Actions.LoadAllSettingsAction -> {
                store.dispatch(ChangeNumQuestionsSettingsAction(settings.loadNumRounds()))
            }
        }
        return nextDispatcher(action)
    }
}