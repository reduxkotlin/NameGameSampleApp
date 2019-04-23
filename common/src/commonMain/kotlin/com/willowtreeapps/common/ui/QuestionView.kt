package com.willowtreeapps.common.ui

import com.willowtreeapps.common.QuestionViewState

interface QuestionView : View<QuestionPresenter> {

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
