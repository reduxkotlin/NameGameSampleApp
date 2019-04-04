package com.willowtreeapps.common.ui

import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.View

interface QuestionView : View {

    fun showProfile(viewState: QuestionViewState)

    fun showCorrectAnswer(viewState: QuestionViewState, isEndGame: Boolean)

    fun showWrongAnswer(viewState: QuestionViewState, isEndGame: Boolean)

    fun setTimerText(viewState: QuestionViewState)

    fun showTimesUp(viewState: QuestionViewState, isEndGame: Boolean)
}
