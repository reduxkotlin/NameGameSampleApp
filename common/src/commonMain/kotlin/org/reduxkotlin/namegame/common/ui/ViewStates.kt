package org.reduxkotlin.namegame.common.ui

import org.reduxkotlin.namegame.common.QuestionCategoryId

data class SettingsViewState(val numQuestions: Int,
                             val categoryId: QuestionCategoryId,
                             val categoryDisplayValues: List<String>,
                             val isMicModeEnabled: Boolean)

data class QuestionViewState(
        val title: String,
        val itemImageUrl: String,
        val currentQuestion: String,
        val numQuestions: String,
        val button1Text: String,
        val button2Text: String,
        val button3Text: String,
        val button4Text: String,
        val correctBtnNum: Int,
        val nextButtonVisible: Boolean,
        val endButtonVisible: Boolean,
        val timerText: String,
        val selectedBtnNum: Int)


data class GameResultsViewState(val resultsText: String,
                                val messageText: String)

