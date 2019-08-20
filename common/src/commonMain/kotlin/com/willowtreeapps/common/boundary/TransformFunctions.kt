package com.willowtreeapps.common.boundary

import com.willowtreeapps.common.*
import com.willowtreeapps.common.ui.GameResultsViewState
import com.willowtreeapps.common.ui.QuestionViewState
import com.willowtreeapps.common.ui.SettingsViewState

/**
 * Functions for transforming AppState data into ViewState data to be used by Views.
 */
fun AppState.toQuestionViewState(): QuestionViewState {
    val item = currentQuestionItem()
    val imageUrl = item.imageUrl
    val choice1 = currentQuestion?.choices?.get(0)!!.displayName()
    val choice2 = currentQuestion?.choices?.get(1)!!.displayName()
    val choice3 = currentQuestion?.choices?.get(2)!!.displayName()
    val choice4 = currentQuestion?.choices?.get(3)!!.displayName()
    val correctBtnNum = currentQuestion?.choices?.indexOfFirst { it.id == item.id }!! + 1
    var selectedBtnNum = currentQuestion?.choices?.indexOfFirst {
        it.equalsDisplayName(currentQuestion?.answerName ?: "")
    }
    if (selectedBtnNum != null) {
        selectedBtnNum += 1
    }
    return QuestionViewState(title = questionTitle,
            itemImageUrl = imageUrl,
            currentQuestion = (currentQuestionIndex + 1).toString(),
            numQuestions = questions.size.toString(),
            button1Text = choice1,
            button2Text = choice2,
            button3Text = choice3,
            button4Text = choice4,
            correctBtnNum = correctBtnNum,
            nextButtonVisible = this.waitingForNextQuestion && !isGameComplete(),
            endButtonVisible = isGameComplete(),
            timerText = timerText,
            selectedBtnNum = selectedBtnNum ?: -1)
}

fun AppState.toGameResultsViewState(): GameResultsViewState {
    val percentage = ((numCorrect.toFloat() / questions.size) * 100).toInt()
    val messageText = with(gameResultResponses) {
        when (percentage) {
            100 -> perfect
            in 80..99 -> good
            in 50..79 -> ok
            in 10..49 -> bad
            0 -> zero
            else -> throw IllegalStateException("Error in toGameResultsViewState when statement or invalid game state")
        }
    }
    return GameResultsViewState(resultsText = gameTotals(),
            messageText = messageText)
}

fun UserSettings.toViewState(): SettingsViewState = SettingsViewState(
        numQuestions = numQuestions,
        categoryId = categoryId,
        categoryDisplayValues = QuestionCategoryId.displayNameList,
        isMicModeEnabled = microphoneMode
)

private fun AppState.gameTotals() = "$numCorrect out of ${questions.size}"

