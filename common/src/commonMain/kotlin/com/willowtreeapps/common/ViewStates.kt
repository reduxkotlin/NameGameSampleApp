package com.willowtreeapps.common

sealed class ViewStates

class RoundViewState(
        val title: String,
        val profileImageUrl: String,
        val currentRound: String,
        val numRounds: String,
        val button1Text: String,
        val button2Text: String,
        val button3Text: String,
        val button4Text: String)

