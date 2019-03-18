package com.willowtreeapps.common.view

import com.willowtreeapps.common.repo.Profile

interface View

interface StartScreen: View {
    fun showLoading()
    fun hideLoading()
}

interface QuestionScreen: View {
    fun showLoading()

    fun showProfile(profile: Profile)

    fun showCorrectAnswer(profileId: String)

    fun showWrongAnswer(profileId: String)
}

interface RoundCompleteScreen: View {
    fun showResults()
}