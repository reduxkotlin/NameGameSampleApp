package com.willowtreeapps.common.boundary

import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.RoundViewState
import com.willowtreeapps.common.repo.Profile

fun AppState.toRoundViewState(): RoundViewState {
    val profile = rounds[currentRoundIndex].profile()
    val imageUrl = profile.headshot.url
    val choice1 = getProfile(currentRound.choices[0])!!.displayName()
    val choice2 = getProfile(currentRound.choices[1])!!.displayName()
    val choice3 = getProfile(currentRound.choices[2])!!.displayName()
    val choice4 = getProfile(currentRound.choices[3])!!.displayName()
    return RoundViewState(title = "Who is this?",
            profileImageUrl = "https:/$imageUrl",
            currentRound = (currentRoundIndex + 1).toString(),
            numRounds = this.numRounds.toString(),
            button1Text = choice1,
            button2Text = choice2,
            button3Text = choice3,
            button4Text = choice4)
}

fun Profile.displayName() = "$firstName $lastName"



