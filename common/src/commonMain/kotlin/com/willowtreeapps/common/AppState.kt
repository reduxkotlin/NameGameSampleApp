package com.willowtreeapps.common

data class AppState(val isLoadingItems: Boolean = false,
                    val items: List<Item> = listOf(),
                    val errorLoadingItems: Boolean = false,
                    val errorMsg: String = "",
                    val currentQuestionIndex: Int = 0,
                    val waitingForNextQuestion: Boolean = false,
                    val waitingForResultsTap: Boolean = false,
                    val questionClock: Int = -1,
                    val questions: List<Question> = listOf(),
                    val settings: UserSettings = UserSettings.defaults()) {
    companion object {
        val INITIAL_STATE = AppState()
    }

    val timerText: String
        get() = if (questionClock < 0) "" else if (questionClock >= 0) questionClock.toString() else "TIME'S UP!!"

    val currentQuestion: Question?
        get() = if (questions.size > currentQuestionIndex)
            questions[currentQuestionIndex]
        else
            null

    fun getItem(id: ItemId?) = items.find { it.id == id }

    fun currentQuestionItem() = getItem(currentQuestion?.itemId)!!

    fun isGameComplete(): Boolean = currentQuestionIndex >= questions.size || (currentQuestionIndex == questions.size - 1 && questions[currentQuestionIndex].status != Question.Status.UNANSWERED)

    val numCorrect: Int
        get() = questions.count { it.status == Question.Status.CORRECT }
}

inline class ItemId(val id: String)

data class Question(val itemId: ItemId,
                    val choices: List<ItemId>,
                    val status: Status = Status.UNANSWERED,
                    val answerName: String? = null) {
    enum class Status {
        UNANSWERED,
        CORRECT,
        INCORRECT,
        TIMES_UP
    }
}

data class Item(val id: ItemId,
                val imageUrl: String,
                val firstName: String,
                val lastName: String) {

    fun displayName() = "$firstName $lastName"

    fun matches(name: String): Boolean {
        return displayName() == name
    }

}

enum class QuestionCategoryId(val displayName: String) {
    WILLOW_TREE("WillowTree"),
    DOGS("Dogs"),
    CATS("Cats");

    companion object {
        val displayNameList by lazy {
            values().map { it.displayName }
        }

        fun fromOrdinal(ordinal: Int) = values()[ordinal]
    }
}

data class UserSettings(val numQuestions: Int,
                        val categoryId: QuestionCategoryId) {
    companion object {
        fun defaults() = UserSettings(3, categoryId = QuestionCategoryId.CATS)
    }
}

