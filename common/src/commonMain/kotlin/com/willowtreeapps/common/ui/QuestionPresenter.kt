package com.willowtreeapps.common.ui

import com.beyondeye.reduks.*
import com.willowtreeapps.common.*
import com.willowtreeapps.common.boundary.toQuestionViewState
import com.willowtreeapps.common.util.VibrateUtil
import com.willowtreeapps.common.util.debounce
import com.willowtreeapps.common.util.isAndroid


class QuestionPresenter(
        private val engine: GameEngine,
        private val vibrateUtil: VibrateUtil,
        private val timerThunks: TimerThunks) : Presenter<QuestionView>() {

    override fun recreateView() {
        view?.showProfileNotAnimated(engine.state.toQuestionViewState())
    }

    //TODO consider SelectorSubscriberFn take coroutineContext as param so activity.runOnUiThread is not needed
    override fun makeSubscriber() = SelectorSubscriberFn(engine.appStore) {
        withSingleField({ it.questionClock }, { view?.setTimerText(state.toQuestionViewState()) })
        withSingleField({
            it.currentQuestion?.itemId?.id ?: Any()
        }, { view?.showProfile(state.toQuestionViewState()) })

        withSingleField({ it.waitingForNextQuestion }) {
            if (state.waitingForNextQuestion) {
                if (state.settings.microphoneMode) {
                    view?.closeMic()
                    if (!state.isGameComplete()) {
                        store.dispatch(timerThunks.dispatchDelayed(4000, Actions.NextQuestionAction()))
                    }
                }
                when (state.currentQuestion?.status) {
                    Question.Status.CORRECT -> {
                        view?.showCorrectAnswer(state.toQuestionViewState(), state.isGameComplete())
                    }
                    Question.Status.INCORRECT -> {
                        vibrateUtil.vibrate()
                        view?.showWrongAnswer(state.toQuestionViewState(), state.isGameComplete())
                    }
                    Question.Status.TIMES_UP -> {
                        vibrateUtil.vibrate()
                        view?.showTimesUp(state.toQuestionViewState(), state.isGameComplete())
                    }
                    Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
                }
            }
        }
    }

    private val debouncedNamePicked: ((String) -> Unit) =
            debounce(400, engine.uiContext) { name ->
                Logger.d("choose name: $name")
                engine.dispatch(Actions.NamePickedAction(name))
                engine.dispatch(timerThunks.stopTimer())
                view?.closeMic()
            }

    fun namePicked(name: String) {
        //check for android here, because performance is a bit different and better UX without debounce
        if (isAndroid()) {
            engine.dispatch(Actions.NamePickedAction(name))
            engine.dispatch(timerThunks.stopTimer())
            view?.closeMic()
        } else {
            debouncedNamePicked(name)
        }
    }

    fun nextTapped() {
        engine.dispatch(timerThunks.cancelDelayed())
        engine.dispatch(Actions.NextQuestionAction())
    }

    fun profileImageIsVisible() {
        if (!engine.state.isCurrentQuestionAnswered()) {
            engine.dispatch(timerThunks.startCountDownTimer(5))
            if (engine.state.settings.microphoneMode) {
                view?.openMic()
            }
        }
    }

    fun endGameTapped() {
        engine.dispatch(Actions.GameCompleteAction())
    }

    fun onBackPressed() {
        engine.dispatch(Actions.StartOverAction())
        engine.dispatch(timerThunks.stopTimer())
    }

}