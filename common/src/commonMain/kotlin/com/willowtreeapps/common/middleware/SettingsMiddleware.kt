package com.willowtreeapps.common.middleware

import org.reduxkotlin.GetState
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
 * Save and Loads user settingsRepo from local storage
 */
internal class SettingsMiddleware(private val settingsRepo: LocalStorageSettingsRepository,
                                  private val backgroundContext: CoroutineContext): CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = backgroundContext + Job()


    fun dispatch(getState: GetState<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        launch {
            when (action) {
                is ChangeNumQuestionsSettingsAction -> settingsRepo.numRounds = action.num

                is Actions.ChangeCategorySettingsAction -> settingsRepo.categoryId = action.categoryId

                is Actions.LoadAllSettingsAction -> {
                    val settings = UserSettings(numQuestions = settingsRepo.numRounds,
                            categoryId = settingsRepo.categoryId,
                            microphoneMode = settingsRepo.microphoneMode)
                    nextDispatcher(Actions.SettingsLoadedAction(settings))
                }

                is Actions.ChangeMicrophoneModeSettingsAction ->  settingsRepo.microphoneMode = action.enabled
            }
        }
        return nextDispatcher(action)
    }
}

