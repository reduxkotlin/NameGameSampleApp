package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.Actions.ChangeNumQuestionsSettingsAction
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.UserSettings
import com.willowtreeapps.common.repo.LocalStorageSettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Save and Loads user settings from local storage
 */
internal class SettingsMiddleware(private val settings: LocalStorageSettingsRepository,
                         private val backgroundContext: CoroutineContext): CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = backgroundContext + Job()


    fun dispatch(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        launch {
            when (action) {
                is ChangeNumQuestionsSettingsAction -> settings.numRounds = action.num

                is Actions.ChangeCategorySettingsAction -> settings.categoryId = action.categoryId

                is Actions.LoadAllSettingsAction -> {
                    val settings = UserSettings(numQuestions = settings.numRounds, categoryId = settings.categoryId)
                    store.dispatch(Actions.SettingsLoadedAction(settings))
                }
            }
        }
        return nextDispatcher(action)
    }
}