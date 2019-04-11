package com.willowtreeapps.common.boundary

import com.willowtreeapps.common.*

/**
 * Functions for transforming AppState data into ViewState data to be used by Views.
 */
fun AppState.toQuestionViewState(): QuestionViewState {
    val item = currentQuestionItem()
    val imageUrl = item.imageUrl
    val choice1 = getItem(currentQuestion?.choices?.get(0))!!.displayName()
    val choice2 = getItem(currentQuestion?.choices?.get(1))!!.displayName()
    val choice3 = getItem(currentQuestion?.choices?.get(2))!!.displayName()
    val choice4 = getItem(currentQuestion?.choices?.get(3))!!.displayName()
    val correctBtnNum = currentQuestion?.choices?.indexOfFirst { it == item.id }!! + 1
    var selectedBtnNum = currentQuestion?.choices?.indexOfFirst { getItem(it)?.matches(currentQuestion?.answerName ?: "") ?: false}
    if (selectedBtnNum != null) {
        selectedBtnNum += 1
    }
    return QuestionViewState(title = "Who is this?",
            itemImageUrl = imageUrl,
            currentQuestion = (currentQuestionIndex + 1).toString(),
            numQuestions = questions.size.toString(),
            button1Text = choice1,
            button2Text = choice2,
            button3Text = choice3,
            button4Text = choice4,
            correctBtnNum = correctBtnNum,
            timerText = timerText,
            selectedBtnNum =  selectedBtnNum ?: -1)
}

fun AppState.toGameResultsViewState(): GameResultsViewState {
    val percentage = ((numCorrect.toFloat() / questions.size) * 100).toInt()
    val messageText = when (percentage) {
        100 -> perfectScoreResponses.takeRandom()
        in 80..99 -> goodScoreResponses.takeRandom()
        in 50..79 -> okScoreResponses.takeRandom()
        in 10..49 -> badScoreResponses.takeRandom()
        0 -> zeroScoreResponses.takeRandom()
        else -> throw IllegalStateException("Error in toGameResultsViewState when statement or invalid game state")
    }
    return GameResultsViewState(resultsText = gameTotals(),
            messageText = messageText)
}

fun UserSettings.toViewState(): SettingsViewState = SettingsViewState(this.numQuestions, this.categoryId)

//TODO should this be here?
private fun AppState.roundTotals() = "${currentQuestionIndex + 1} out of ${questions.size}"

private fun AppState.gameTotals() = "$numCorrect out of ${questions.size}"

