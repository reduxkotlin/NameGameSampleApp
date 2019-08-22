package org.reduxkotlin.namegame.common.ui

import org.reduxkotlin.namegame.common.Question
import org.reduxkotlin.namegame.common.boundary.toQuestionViewState
import org.reduxkotlin.namegame.common.middleware.UiActions

interface QuestionView : GameBaseView {
    override fun presenter() = questionPresenter

    fun showProfile(viewState: QuestionViewState)

    fun showProfileNotAnimated(viewState: QuestionViewState) {
        //only needed on Android
    }

    fun showCorrectAnswer(viewState: QuestionViewState, isEndGame: Boolean)

    fun showWrongAnswer(viewState: QuestionViewState, isEndGame: Boolean)

    fun setTimerText(viewState: QuestionViewState)

    fun showTimesUp(viewState: QuestionViewState, isEndGame: Boolean)

    fun openMic()

    fun closeMic()
}

val questionPresenter = presenter<QuestionView> {{
    +{ state.questionClock }+ { setTimerText(state.toQuestionViewState()) }

    +{ state.currentQuestion.itemId.id } + { showProfile(state.toQuestionViewState()) }

    +{ state.waitingForNextQuestion } + {
        if (state.waitingForNextQuestion) {
            if (state.settings.microphoneMode) {
                closeMic()
                if (!state.isGameComplete()) {
                    store.dispatch(UiActions.NextQuestionDelayed())
                }
            }
            when (state.currentQuestion.status) {
                Question.Status.CORRECT -> {
                    showCorrectAnswer(state.toQuestionViewState(), state.isGameComplete())
                }
                Question.Status.INCORRECT -> {
                    store.dispatch(UiActions.VibrateAction())
                    showWrongAnswer(state.toQuestionViewState(), state.isGameComplete())
                }
                Question.Status.TIMES_UP -> {
                    store.dispatch(UiActions.VibrateAction())
                    showTimesUp(state.toQuestionViewState(), state.isGameComplete())
                }
                Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
            }
        }
    }
}}

