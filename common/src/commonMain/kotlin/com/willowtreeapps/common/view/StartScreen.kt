package com.willowtreeapps.common.view

import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.QuestionViewState

interface View

interface StartScreen : View {
    fun showLoading()
    fun hideLoading()
}

interface QuestionScreen : View {

    fun showProfile(viewState: QuestionViewState)

    fun showCorrectAnswer()

    fun showWrongAnswer()

    fun showCorrectAnswerEndGame()

    fun showWrongAnswerEndGame()
}

interface GameResultsScreen : View {
    fun showResults(viewState: GameResultsViewState)
}