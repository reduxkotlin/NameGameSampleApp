package org.reduxkotlin.namegame

import org.reduxkotlin.namegame.common.repo.MockRepositoryFactory
import org.reduxkotlin.namegame.common.repo.ProfileItemRepository
import org.reduxkotlin.namegame.common.repo.generateQuestions
import org.reduxkotlin.namegame.common.util.takeRandomDistinct
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.reduxkotlin.namegame.common.*
import java.lang.Exception
import java.lang.IllegalStateException

class ReducersTest {

    @Test
    fun `take random N items`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        val randomList = list.takeRandomDistinct(10)
        assert(randomList.size == 10)
        assert(randomList.distinctBy { it }.size == 10)
    }

    @Test
    fun `throw exception on N greater than size`() {
        val list = listOf(1, 2)
        val value = try {
            list.takeRandomDistinct(3)
        } catch (e: Exception) {
            e
        }

        assert(value is IllegalStateException)
    }
//
//    @Test
//    fun `generate N distinct random rounds`() {
//
//        val itemHolder = runBlocking { ProfileItemRepository(MockRepositoryFactory().success()).fetchItems(10) }.response
//        val rounds = generateQuestions(itemHolder?.items!!, 10)
//
//        assertEquals(10, rounds.size)
//        assertEquals(10, rounds.distinctBy { it.itemId }.size)
//    }

    @Test
    fun `isLoadingProfiles set true`() {
        val final = reducer(generateInitialTestState(), Actions.FetchingItemsStartedAction()) as AppState

        assertTrue(final.isLoadingItems)
    }

//    @Test
//    fun `isLoadingProfiles set false on success`() {
//        val initial = generateInitialTestState().copy(isLoadingItems = true)
//        val final = reducer(initial, Actions.FetchingItemsSuccessAction(runBlocking { ProfileItemRepository(MockRepositoryFactory().success()).fetchItems(10) }.response!!)) as AppState
//
//        assertFalse(final.isLoadingItems)
//    }

    @Test
    fun `isLoadingProfiles set false on failure`() {
        val initial = generateInitialTestState().copy(isLoadingItems = true)
        val final = reducer(initial, Actions.FetchingItemsFailedAction("Test failure")) as AppState

        assertFalse(final.isLoadingItems)
    }

    @Test
    fun `NextQuestionAction - increments currentRoundIndex`() {
        val initial = generateInitialTestState()
        val final = reducer(initial, Actions.NextQuestionAction()) as AppState

        assertEquals(1, final.currentQuestionIndex)
    }

    @Test
    fun `NextQuestionAction - sets waitingForNextQuestion = false`() {
        val initial = generateInitialTestState()
        val final = reducer(initial, Actions.NextQuestionAction()) as AppState

        assertEquals(false, final.waitingForNextQuestion)
    }

    @Test
    fun `NamePickedAction - mark current round as CORRECT when name matches`() {
        val initial = generateInitialTestState()
        val answer = initial.currentQuestionItem().displayName()

        val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

        assertEquals(Question.Status.CORRECT, final.currentQuestion?.status)
    }

    @Test
    fun `NamePickedAction - mark current round as INCORRECT when name doesn't matches`() {
        val initial = generateInitialTestState()
        val answer = "wrong answer"

        val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

        assertEquals(Question.Status.INCORRECT, final.currentQuestion?.status)
    }

    @Test
    fun `NamePickedAction - mark INCORRECT when name matches incorrect choice`() {
        val initial = staticTestState(stanLee)
        val answer = justinTimberlake.displayName()

        val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

        assertEquals(Question.Status.INCORRECT, final.currentQuestion?.status)
    }

    @Test
    fun `NamePickedAction - set answerName to null if no matches`() {
        repeat(200) {
            val initial = generateInitialTestState()
            val answer = "wrong answer"

            val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

            assertEquals(null, final.currentQuestion?.answerName)
        }
    }

    @Test
    fun `NamePickedAction - set answerName to display name on fuzzy match`() {
        repeat(200) {
            val initial = staticTestState(stanLee)
            val answer = "stan bee"

            val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

            assertEquals("Stan Lee", final.currentQuestion?.answerName)
        }
    }

    @Test
    fun `NamePickedAction - status set to CORRECT for fuzzy match`() {
        repeat(200) {
            val initial = staticTestState(stanLee)
            val answer = "stan bee"

            val final = reducer(initial, Actions.NamePickedAction(answer)) as AppState

            assertEquals(Question.Status.CORRECT, final.currentQuestion?.status)
        }
    }

    @Test
    fun `mark current round as TIMES when time is up`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.TimesUpAction()) as AppState

        assertEquals(Question.Status.TIMES_UP, final.currentQuestion?.status)
    }

    @Test
    fun `decrement timer`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.DecrementCountDownAction()) as AppState

        assertEquals(initial.questionClock - 1, final.questionClock)
    }

    @Test
    fun `start question timer with initial value`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.StartQuestionTimerAction(10)) as AppState

        assertEquals(10, final.questionClock)
    }

    @Test
    fun `ChangeNumQuestionsAction should update AppState`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.ChangeNumQuestionsSettingsAction(10)) as AppState

        assertEquals(10, final.settings.numQuestions)
    }

    @Test
    fun `ChangeMicrophoneModeAction should update settings`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.ChangeMicrophoneModeSettingsAction(true)) as AppState

        assertEquals(true, final.settings.microphoneMode)
    }



    private fun generateInitialTestState(): AppState {
        val initialState = reducer(AppState(), Actions.FetchingItemsSuccessAction(runBlocking { ProfileItemRepository(MockRepositoryFactory().success()).fetchItems(10) }.response!!)) as AppState
        return initialState
    }

    private val justinTimberlake = Item(ItemId("0"), "", "Justin", "Timberlake")
    private val bobEvans = Item(ItemId("1"), "", "Bob", "Evans")
    private val stanLee = Item(ItemId("2"), "", "Stan", "Lee")
    private val lukeSkywalker = Item(ItemId("3"), "", "Luke", "Skywalker")

    fun staticTestState(correctAnswer: Item): AppState {

        val items = listOf(
                justinTimberlake,
                bobEvans,
                stanLee,
                lukeSkywalker
        )
        val questions = listOf(
                Question(itemId = correctAnswer.id,
                        choices = items,
                        status = Question.Status.UNANSWERED
                )
        )
        return AppState(isLoadingItems = false,
                items = items,
                questions = questions
        )
    }

}