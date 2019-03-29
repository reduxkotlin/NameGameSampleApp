package com.willowtreeapps.common

sealed class ViewStates

class QuestionViewState(
        val title: String,
        val profileImageUrl: String,
        val currentQuestion: String,
        val numQuestions: String,
        val button1Text: String,
        val button2Text: String,
        val button3Text: String,
        val button4Text: String,
        val correctBtnNum: Int,
        val selectedBtnNum: Int)


data class GameResultsViewState(val resultsText: String,
                                val messageText: String)


val perfectScoreResponses = listOf(
        "Perfect!!",
        "Wow, perfection!",
        "Great Job - 100% !!!",
        "Ok, you've worked here a while.")

val goodScoreResponses = listOf(
        "Nice!!",
        "Pretty Good!",
        "Good, but you still meet a few folks!!!",
        "Good Job!")

val okScoreResponses = listOf(
        "Not bad, but you can do better!!",
        "Still meeting people?",
        "Not that great \nYou could stand some practice.",
        "A few more times are I think you'll learn more names.")

val badScoreResponses = listOf(
        "Ok, do you even work here??",
        "Practice, Practice, Practice. \nYou'll get it!",
        "Wow, that is not good.  \nNew to the company?",
        "No worries, try again!")

val zeroScoreResponses = listOf(
        "Did you even try??",
        "Not even one correct??  \nWow...want to try again?",
        "Even my cat gets a few right....")
