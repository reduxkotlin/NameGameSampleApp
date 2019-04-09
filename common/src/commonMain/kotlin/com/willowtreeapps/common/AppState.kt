package com.willowtreeapps.common

import com.willowtreeapps.common.repo.Profile

data class AppState(val isLoadingProfiles: Boolean = false,
                    val profiles: List<Profile> = listOf(),
                    val errorLoadingProfiles: Boolean = false,
                    val errorMsg: String = "",
                    val currentQuestionIndex: Int = 0,
                    val waitingForNextQuestion: Boolean = false,
                    val waitingForResultsTap: Boolean = false,
                    val questionClock: Int = 0,
                    val questions: List<Question> = listOf(),
                    val settings: UserSettings = UserSettings.defaults()) {
    companion object {
        val INITIAL_STATE = AppState()
    }

    fun Question.profile(): Profile = profiles.find { ProfileId(it.id) == this.profileId }!!

    val timerText: String
        get() = if (questionClock >= 0) questionClock.toString() else "TIME'S UP!!"

    val currentQuestion: Question?
        get() = if (questions.size > currentQuestionIndex)
            questions[currentQuestionIndex]
        else
            null

    fun getProfile(id: ProfileId?) = profiles.find { it.id == id?.id }

    fun currentQuestionProfile() = getProfile(currentQuestion?.profileId)!!

    fun isGameComplete(): Boolean = currentQuestionIndex >= questions.size || (currentQuestionIndex == questions.size - 1 && questions[currentQuestionIndex].status != Question.Status.UNANSWERED)

    val numCorrect: Int
        get() = questions.count { it.status == Question.Status.CORRECT }
}

inline class ProfileId(val id: String)

data class Question(val profileId: ProfileId,
                    val choices: List<ProfileId>,
                    val status: Status = Status.UNANSWERED,
                    val answerName: String? = null) {
    enum class Status {
        UNANSWERED,
        CORRECT,
        INCORRECT,
        TIMES_UP
    }
}

data class UserSettings(val numQuestions: Int) {
    companion object {
        fun defaults() = UserSettings(3)
    }
}

