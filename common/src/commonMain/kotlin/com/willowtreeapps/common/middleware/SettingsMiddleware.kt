package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.Actions.ChangeNumQuestionsSettingsAction
import com.willowtreeapps.common.UserSettings
import com.willowtreeapps.common.repo.LocalStorageSettingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.Store
import kotlin.coroutines.CoroutineContext

/**
 * Save and Loads user settingsRepo from local storage
 */
internal class SettingsMiddleware(private val settingsRepo: LocalStorageSettingsRepository,
                                  private val backgroundContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = backgroundContext + Job()


    fun dispatch(store: Store) = { next: Dispatcher ->
        { action: Any ->
            launch {
                when (action) {
                    is ChangeNumQuestionsSettingsAction -> settingsRepo.numRounds = action.num

                    is Actions.ChangeCategorySettingsAction -> settingsRepo.categoryId = action.categoryId

                    is Actions.LoadAllSettingsAction -> {
                        val settings = UserSettings(numQuestions = settingsRepo.numRounds,
                                categoryId = settingsRepo.categoryId,
                                microphoneMode = settingsRepo.microphoneMode)
                        next(Actions.SettingsLoadedAction(settings))
                    }

                    is Actions.ChangeMicrophoneModeSettingsAction -> settingsRepo.microphoneMode = action.enabled
                }
            }
            next(action)
        }
    }
}

