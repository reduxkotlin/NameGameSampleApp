package com.willowtreeapps.common

import com.willowtreeapps.common.middleware.*
import com.willowtreeapps.common.repo.LocalStorageSettingsRepository
import com.willowtreeapps.common.repo.userSettings
import com.willowtreeapps.common.util.VibrateUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.reduxkotlin.*
import kotlin.coroutines.CoroutineContext

class GameEngine(navigator: Navigator,
                 application: Any = Any(),
                 networkContext: CoroutineContext,
                 private val uiContext: CoroutineContext) {
    private val timerThunks = TimerThunks(networkContext)
    private val networkThunks = NetworkThunks(networkContext)
    val vibrateUtil = VibrateUtil(application)
    private val localStorageSettingsRepository by lazy { LocalStorageSettingsRepository(userSettings(application)) }

    val appStore by lazy {
        createStore(reducer, AppState.INITIAL_STATE,
                compose(listOf(presenterEnhancer(uiContext),
                        applyMiddleware(createThunkMiddleware(),
                                uiMiddleware(networkThunks, timerThunks, uiContext),
                                navigationMiddleware(navigator),
                                loggerMiddleware,
                                settingsMiddleware(localStorageSettingsRepository, networkContext)))))
    }

    init {
        CoroutineScope(uiContext).launch {
            appStore.dispatch(Actions.LoadAllSettingsAction())
        }
    }

    fun dispatch(action: Any) {
        CoroutineScope(uiContext).launch {
            appStore.dispatch(action)
        }
    }

    val state: AppState
        get() = appStore.state

}
