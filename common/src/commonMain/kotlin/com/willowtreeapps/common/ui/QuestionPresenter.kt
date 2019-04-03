package com.willowtreeapps.common.ui

import com.beyondeye.reduks.SelectorBuilder
import com.beyondeye.reduks.Store
import com.beyondeye.reduks.StoreSubscriberBuilderFn
import com.beyondeye.reduks.StoreSubscriberFn
import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Presenter
import com.willowtreeapps.common.Question
import com.willowtreeapps.common.boundary.toQuestionViewState
import com.willowtreeapps.common.util.VibrateUtil


class QuestionPresenter(view: QuestionView,
                        val store: Store<AppState>,
                        private val vibrateUtil: VibrateUtil) : Presenter {
    private val subscriber = StoreSubscriberBuilderFn<AppState> { store ->
        val selBuilder = SelectorBuilder<AppState>()
        val profileSelector = selBuilder.withSingleField { currentQuestion?.profileId?.id ?: Any() }
        StoreSubscriberFn {
            val state = store.state
            profileSelector.onChangeIn(state) {
                view.showProfile(state.toQuestionViewState())
            }

            if (state.isGameComplete()) {
                if (state.waitingForNextQuestion) {
                    when (state.currentQuestion?.status) {
                        Question.Status.CORRECT -> {
                            view.showCorrectAnswerEndGame(state.toQuestionViewState())
                        }
                        Question.Status.INCORRECT -> {
                            vibrateUtil.vibrate()
                            view.showWrongAnswerEndGame(state.toQuestionViewState())
                        }
                        Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
                    }
                }
            } else {
                if (state.waitingForNextQuestion) {
                    when (state.currentQuestion?.status) {
                        Question.Status.CORRECT -> {
                            view.showCorrectAnswer(state.toQuestionViewState())
                        }
                        Question.Status.INCORRECT -> {
                            vibrateUtil.vibrate()
                            view.showWrongAnswer(state.toQuestionViewState())
                        }
                        Question.Status.UNANSWERED -> throw IllegalStateException("Question status cannot be Unanswered when waiting for next round == true")
                    }
                }
            }
        }
    }.build(store)

    override fun onStateChange(state: AppState) {
        subscriber.onStateChange()
    }

    fun namePicked(name: String) {
        store.dispatch(Actions.NamePickedAction(name))
    }

    fun nextTapped() {
        store.dispatch(Actions.NextQuestionAction())
    }

    fun endGameTapped() {
        store.dispatch(Actions.GameCompleteAction())
    }

    fun onBackPressed() {
        store.dispatch(Actions.StartOverAction())
    }

}