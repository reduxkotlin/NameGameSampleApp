package com.willowtreeapps.common.middleware

import com.beyondeye.reduks.Store
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerMiddleware(val uiCoroutineContext: CoroutineContext) {
    private var timerJobs = mutableMapOf<String, Job>()

    fun dispatch(store: Store<AppState>, nextDispatcher: (Any) -> Any, action: Any): Any {
        if (action is Actions.StartTimerAction) {
            if (timerJobs.contains(action.name)) {
                timerJobs[action.name]?.cancel()
            }
            timerJobs[action.name] = launchTimer(action.intervalMs, action.work, CoroutineScope(uiCoroutineContext))
        }
        return nextDispatcher(action)
    }

    private fun launchTimer(xMs: Long, f: () -> Unit, coroutineScope: CoroutineScope): Job {
        return coroutineScope.launch {
            while (isActive) {
                delay(xMs)
                f()
            }
        }
    }

}