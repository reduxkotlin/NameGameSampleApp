package com.willowtreeapps.common

import com.willowtreeapps.common.Actions.*
import com.willowtreeapps.common.middleware.UiActions
import com.willowtreeapps.common.util.NO_MATCH
import com.willowtreeapps.common.util.match
import org.reduxkotlin.*

/**
 * Reducers and functions used by reducers are in this file.  Functions must be pure functions without
 * side effects.
 */
val reducer: Reducer<AppState> = { state: AppState, action ->
    when (action) {
        is ActionTypes.INIT -> {
            AppState.INITIAL_STATE
        }
        is FetchingItemsStartedAction -> state.copy(isLoadingItems = true)
        is FetchingItemsSuccessAction -> {
            state.copy(isLoadingItems = false,
                    items = action.itemsHolder.items,
                    questionTitle = action.itemsHolder.questionTitle,
                    questions = action.itemsHolder.questions)
        }
        is FetchingItemsFailedAction -> state.copy(isLoadingItems = false, errorLoadingItems = true, errorMsg = action.message)
        is NamePickedAction -> {
            val answerName: String?
            val status = if (state.currentQuestionItem().equalsDisplayName(action.name)) {
                answerName = action.name
                Question.Status.CORRECT
            } else {
                val correctIndex = state.currentQuestion?.choices?.indexOfFirst { it.id == state.currentQuestion?.itemId }
                val matchingIndex = match(action.name, state.currentQuestion!!.choices.map { it.displayName() })
                when (matchingIndex) {
                    NO_MATCH -> {
                        answerName = null
                        Question.Status.INCORRECT
                    }
                    correctIndex -> {
                        answerName = state.currentQuestion!!.choices[matchingIndex].displayName()
                        Question.Status.CORRECT
                    }
                    else -> {
                        answerName = state.currentQuestion!!.choices[matchingIndex].displayName()
                        Question.Status.INCORRECT
                    }
                }
            }

            val newQuestions = state.questions.toMutableList()
            newQuestions[state.currentQuestionIndex] = newQuestions[state.currentQuestionIndex].copy(answerName = answerName,
                    status = status,
                    answerNameInterpretedAs = action.name)
            state.copy(questions = newQuestions, waitingForNextQuestion = true)
        }
        is NextQuestionAction -> state.copy(waitingForNextQuestion = false, currentQuestionIndex = state.currentQuestionIndex + 1)
        is GameCompleteAction -> state.copy(waitingForNextQuestion = false, currentQuestionIndex = state.currentQuestionIndex + 1)
        is ResetGameStateAction -> AppState.INITIAL_STATE.copy(settings = state.settings)
        is StartQuestionTimerAction -> state.copy(questionClock = action.initialValue)
        is DecrementCountDownAction -> state.copy(questionClock = state.questionClock - 1)
        is TimesUpAction -> {
            val status = Question.Status.TIMES_UP
            val newQuestions = state.questions.toMutableList()
            newQuestions[state.currentQuestionIndex] = newQuestions[state.currentQuestionIndex].copy(answerName = "", status = status)
            state.copy(questions = newQuestions, waitingForNextQuestion = true, questionClock = -1)
        }

        is ChangeNumQuestionsSettingsAction -> state.copy(settings = state.settings.copy(numQuestions = action.num))
        is ChangeCategorySettingsAction -> state.copy(settings = state.settings.copy(categoryId = action.categoryId))
        is ChangeMicrophoneModeSettingsAction -> state.copy(settings = state.settings.copy(microphoneMode = action.enabled))
        is SettingsLoadedAction -> state.copy(settings = action.settings)

        is LoadAllSettingsAction -> {
            state
        }

        else -> {
//            Logger.d("Action ${action::class.simpleName} not handled")
            state
        }
    }
}
