package com.willowtreeapps.common

import com.beyondeye.reduks.SimpleStore
import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware
import com.willowtreeapps.common.middleware.NavigationMiddleware
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.ViewEffectsMiddleware
import com.willowtreeapps.common.repo.Profile

data class AppState(val isLoadingProfiles: Boolean = false,
                    val profiles: List<Profile> = listOf(),
                    val errorLoadingProfiles: Boolean = false,
                    val errorMsg: String = "",
                    val currentQuestionIndex: Int = 0,
                    val numQuestions: Int = 3,
                    val waitingForNextQuestion: Boolean = false,
                    val waitingForResultsTap: Boolean = false,
                    val questions: List<Question> = listOf()) {
    companion object {
        val INITIAL_STATE = AppState()
    }

    fun Question.profile(): Profile = profiles.find { ProfileId(it.id) == this.profileId }!!

    val currentQuestion: Question?
        get() = if (questions.size > currentQuestionIndex)
            questions[currentQuestionIndex]
        else
            null

    fun getProfile(id: ProfileId?) = profiles.find { it.id == id?.id }

    public fun currentRoundProfile() = getProfile(currentQuestion?.profileId)!!

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
        INCORRECT
    }
}

class GameEngine(navigator: Navigator) {
    val navigationMiddleware = NavigationMiddleware(navigator)
    val viewEffectsMiddleware = ViewEffectsMiddleware()
    val appStore by lazy {
        SimpleStore(AppState.INITIAL_STATE, reducer)
                .applyMiddleware(::thunkMiddleware,
                        viewEffectsMiddleware::dispatch,
                        navigationMiddleware::dispatch)
    }
}

