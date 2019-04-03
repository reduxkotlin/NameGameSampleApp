package com.willowtreeapps.common.ui

import com.willowtreeapps.common.QuestionViewState
import com.willowtreeapps.common.View

interface QuestionView : View {

    fun showProfile(viewState: QuestionViewState)

    fun showCorrectAnswer(viewState: QuestionViewState)

    fun showWrongAnswer(viewState: QuestionViewState)

    fun showCorrectAnswerEndGame(viewState: QuestionViewState)

    fun showWrongAnswerEndGame(viewState: QuestionViewState)
}
