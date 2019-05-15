package com.willowtreeapps.common

import com.beyondeye.reduks.Thunk
import com.beyondeye.reduks.ThunkFn
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerThunks(private val backgroundContext: CoroutineContext, private val engine: GameEngine) : CoroutineScope {
    override val coroutineContext = backgroundContext + Job()
    private var countDownTimerJob: Job? = null
    private var delayedJob: Job? = null

    /**
     * Starts a countdown timer that executes every X ms as specified by the action.
     * Only one timer is active at a time.  If called while a timer is active, it will cancel
     * the timer and start the new one.
     */
    fun startCountDownTimer(initialValue: Int): Thunk<AppState> = ThunkFn { dispatcher, state ->
        if (countDownTimerJob == null || countDownTimerJob?.isCompleted == true) {
            Logger.d("Launching new Timer")
            engine.dispatch(Actions.StartQuestionTimerAction(initialValue))
            countDownTimerJob = launchTimer(1000, CoroutineScope(coroutineContext)) {
                if (engine.state.questionClock > 0) {
                    engine.dispatch(Actions.DecrementCountDownAction())
                } else {
                    engine.dispatch(Actions.TimesUpAction())
                    countDownTimerJob?.cancel()

                }
            }
            countDownTimerJob?.invokeOnCompletion {
                Logger.d("TIMERJOB is complete: ${it?.message}")
            }
        }
    }

    fun stopTimer(): Thunk<AppState> = ThunkFn { dispatcher, state ->
        countDownTimerJob?.cancel()
        Unit
    }

    fun dispatchDelayed(delayMs: Long, action: Any): Thunk<AppState> = ThunkFn { dispatcher, state ->
        delayedJob?.cancel()
        delayedJob = CoroutineScope(coroutineContext).launch {
            delay(delayMs)
            engine.dispatch(action)
        }
        Unit
    }

    fun cancelDelayed(): Thunk<AppState> = ThunkFn { dispatcher, state ->
        delayedJob?.cancel()
        Unit
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
