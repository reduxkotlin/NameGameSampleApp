package com.willowtreeapps.common

import com.beyondeye.reduks.Store
import com.beyondeye.reduks.ThunkFn
import com.beyondeye.reduks.ThunkImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerThunks(private val networkContext: CoroutineContext, val store: Store<AppState>) : CoroutineScope {
    override val coroutineContext = networkContext + Job()
    private var timerJob: Job? = null

    /**
     * Starts a countdown timer that executes every X ms as specified by the action.
     * Only one timer is active at a time.  If called while a timer is active, it will cancel
     * the timer and start the new one.
     */
    fun startCountDownTimer(initialValue: Int): ThunkImpl<AppState> = ThunkFn { dispatcher, state ->
        store.dispatch(Actions.StartQuestionTimerAction(initialValue))
        if (timerJob != null) {
            timerJob?.cancel()
        }
        timerJob = launchTimer(1000, CoroutineScope(coroutineContext)) {

            if (store.state.questionClock > 0) {
                store.dispatch(Actions.DecrementCountDownAction())
            } else {
                store.dispatch(Actions.TimesUpAction())
                timerJob?.cancel()
            }
        }
        Any()
    }

    fun stopTimer() {
        timerJob?.cancel()
    }


    private fun launchTimer(xMs: Long, coroutineScope: CoroutineScope, f: () -> Unit): Job {
        return coroutineScope.launch {
            while (true) {
                delay(xMs)
                f()
            }
        }
    }
}
