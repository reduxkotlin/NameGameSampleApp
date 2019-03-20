package com.willowtreeapps.common.view

import com.willowtreeapps.common.GameResultsViewState
import com.willowtreeapps.common.RoundViewState

interface View

interface StartScreen : View {
    fun showLoading()
    fun hideLoading()
}

interface QuestionScreen : View {

    fun showProfile(viewState: RoundViewState)

    fun showCorrectAnswer()

    fun showWrongAnswer()

    fun showCorrectAnswerEndGame()

    fun showWrongAnswerEndGame()

    /**
     * Shows status of last question and a button to end game and advance to summary screen.
     */
    fun showEndOfGame()
}

interface GameResultsScreen : View {
    fun showResults(viewState: GameResultsViewState)
}