package com.willowtreeapps.namegame

import com.willowtreeapps.common.*
import com.willowtreeapps.common.boundary.displayName
import com.willowtreeapps.common.repo.MockRepositoryFactory
import org.junit.Assert.*
import org.junit.Test
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

    @Test
    fun `generate N distinct random rounds`() {
        val profiles = MockRepositoryFactory.getValidResponse()
        val rounds = generateRounds(profiles, 10)

        assertEquals(10, rounds.size)
        assertEquals(10, rounds.distinctBy { it.profileId }.size)
    }

    @Test
    fun `isLoadingProfiles set true`() {
        val final = reducer(generateInitialTestState(), Actions.FetchingProfilesStartedAction())

        assertTrue(final.isLoadingProfiles)
    }

    @Test
    fun `isLoadingProfiles set false on success`() {
        val initial = generateInitialTestState().copy(isLoadingProfiles = true)
        val final = reducer(initial, Actions.FetchingProfilesSuccessAction(MockRepositoryFactory.getValidResponse()))

        assertFalse(final.isLoadingProfiles)
    }

    @Test
    fun `isLoadingProfiles set false on failure`() {
        val initial = generateInitialTestState().copy(isLoadingProfiles = true)
        val final = reducer(initial, Actions.FetchingProfilesFailedAction("Test failure"))

        assertFalse(final.isLoadingProfiles)
    }

    @Test
    fun `NextQuestionAction increments currentRoundIndex`() {
        val initial = generateInitialTestState()
        val final = reducer(initial, Actions.NextQuestionAction())

        assertEquals(1, final.currentQuestionIndex)
    }

    @Test
    fun `NextQuestionAction sets waitingForNextQuestion = false`() {
        val initial = generateInitialTestState()
        val final = reducer(initial, Actions.NextQuestionAction())

        assertEquals(false, final.waitingForNextQuestion)
    }

    @Test
    fun `mark current round as CORRECT when name matches`() {
        val initial = generateInitialTestState()
        val answer = initial.currentQuestionProfile().displayName()

        val final = reducer(initial, Actions.NamePickedAction(answer))

        assertEquals(Question.Status.CORRECT, final.currentQuestion?.status)
    }

    @Test
    fun `mark current round as INCORRECT when name doesn't matches`() {
        val initial = generateInitialTestState()
        val answer = "wrong answer"

        val final = reducer(initial, Actions.NamePickedAction(answer))

        assertEquals(Question.Status.INCORRECT, final.currentQuestion?.status)
    }

    @Test
    fun `mark current round as TIMES when time is up`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.TimesUpAction())

        assertEquals(Question.Status.TIMES_UP, final.currentQuestion?.status)
    }

    @Test
    fun `decrement timer`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.DecrementCountDownAction())

        assertEquals(initial.questionClock - 1, final.questionClock)
    }

    @Test
    fun `start question timer with initial value`() {
        val initial = generateInitialTestState()

        val final = reducer(initial, Actions.StartQuestionTimerAction(10))

        assertEquals(10, final.questionClock)
    }

    private fun generateInitialTestState(): AppState {
        val initialState = reducer(AppState(), Actions.FetchingProfilesSuccessAction(MockRepositoryFactory.getValidResponse()))
        return initialState
    }
}