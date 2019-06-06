package com.willowtreeapps.common

import kotlinx.coroutines.*
import org.reduxkotlin.Thunk
import kotlin.coroutines.CoroutineContext

class TimerThunks(private val backgroundContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext = backgroundContext + Job()
    private var countDownTimerJob: Job? = null
    private var delayedJob: Job? = null

    /**
     * Starts a countdown timer that executes every X ms as specified by the action.
     * Only one timer is active at a time.  If called while a timer is active, it will cancel
     * the timer and start the new one.
     */
    fun startCountDownTimer(initialValue: Int): Thunk = { dispatch, _, _->
        if (countDownTimerJob == null || countDownTimerJob?.isCompleted == true) {
            var localQuestionClock = initialValue
            Logger.d("Launching new Timer")
            Logger.d("QuestionClock before launch: $initialValue")
            dispatch(Actions.StartQuestionTimerAction(initialValue))
            countDownTimerJob = launchTimer(1000, CoroutineScope(coroutineContext)) {
                Logger.d("QuestionClock: $localQuestionClock")
                if (localQuestionClock > 0) {
                    dispatch(Actions.DecrementCountDownAction())
                } else {
                    dispatch(Actions.TimesUpAction())
                    countDownTimerJob?.cancel()
                }
                localQuestionClock--
            }
            countDownTimerJob?.invokeOnCompletion {
                Logger.d("TIMERJOB is complete: ${it?.message}")
            }
        }
    }

    fun stopTimer(): Thunk = { dispatch, _, _ ->
        countDownTimerJob?.cancel()
        Unit
    }

    fun dispatchDelayed(delayMs: Long, action: Any): Thunk = { dispatch, _, _ ->
        delayedJob?.cancel()
        delayedJob = CoroutineScope(coroutineContext).launch {
            delay(delayMs)
            dispatch(action)
        }
        Unit
    }

    fun cancelDelayed(): Thunk = { dispatcher, _, _ ->
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
