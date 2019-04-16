package com.willowtreeapps.common

import com.willowtreeapps.common.Actions.*
import com.willowtreeapps.common.util.TimeUtil
import kotlin.math.abs
import kotlin.random.Random

/**
 * Reducers and functions used by reducers are in this file.  Functions must be pure functions without
 * side effects.
 */
fun reducer(state: AppState, action: Any): AppState =
        when (action) {
            is FetchingItemsStartedAction -> state.copy(isLoadingItems = true)
            is FetchingItemsSuccessAction -> {
                val rounds = generateRounds(action.itemsHolder.items, state.settings.numQuestions)
                state.copy(isLoadingItems = false,
                        items = action.itemsHolder.items,
                        questionTitle = action.itemsHolder.questionTitle,
                        questions = rounds)
            }
            is FetchingItemsFailedAction -> state.copy(isLoadingItems = false, errorLoadingItems = true, errorMsg = action.message)
            is NamePickedAction -> {
                val status = if (state.currentQuestionItem().matches(action.name)) {
                    Question.Status.CORRECT
                } else {
                    Question.Status.INCORRECT
                }
                val newQuestions = state.questions.toMutableList()
                newQuestions[state.currentQuestionIndex] = newQuestions[state.currentQuestionIndex].copy(answerName = action.name, status = status)
                state.copy(questions = newQuestions, waitingForNextQuestion = true)
            }
            is NextQuestionAction -> state.copy(waitingForNextQuestion = false, currentQuestionIndex = state.currentQuestionIndex + 1)
            is GameCompleteAction -> state.copy(waitingForNextQuestion = false, currentQuestionIndex = state.currentQuestionIndex + 1)
            is StartOverAction, is ResetGameStateAction -> AppState.INITIAL_STATE.copy(settings = state.settings)
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
            is SettingsLoadedAction -> state.copy(settings = action.settings)

            else -> throw AssertionError("Action ${action::class.simpleName} not handled")
        }

fun generateRounds(items: List<Item>, n: Int): List<Question> =
        items.takeRandomDistinct(n)
                .map { item ->
                    val choiceList = items.takeRandomDistinct(3).toMutableList()
                    choiceList.add(abs(random.nextInt() % 4), item)

                    Question(itemId = item.id, choices = choiceList
                            .map { it.id })
                }


private val random = Random(TimeUtil.systemTimeMs())

/**
 * Take N distict elements from the list.  Distinct is determined by a comparision of objects in the
 * list.
 * @throws IllegalStateException when n > number of distict elements.
 * @return New immutable list containing N random elements from the given List.
 */
fun <T> List<T>.takeRandomDistinct(n: Int): List<T> {
    val newList = mutableListOf<T>()
    val uniqueItems = this.distinctBy { it }
    if (uniqueItems.size < n) {
        throw IllegalStateException("Unable to get $n unique random elements from given list.")
    }
    while (newList.size < n) {
        val randomIndex = abs(random.nextInt() % uniqueItems.size)
        val next = uniqueItems[randomIndex]
        if (newList.contains(next)) {
            continue
        } else {
            newList.add(next)
        }
    }
    return newList.toList()
}


fun <T> List<T>.takeRandom(): T =
        this[random.nextInt(this.size - 1)]

