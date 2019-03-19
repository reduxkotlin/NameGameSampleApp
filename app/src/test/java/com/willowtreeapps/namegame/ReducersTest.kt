package com.willowtreeapps.namegame

import com.willowtreeapps.common.generateRounds
import com.willowtreeapps.common.repo.MockRepositoryFactory
import com.willowtreeapps.common.takeRandomDistint
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import java.lang.IllegalStateException

class ReducersTest {

    @Test
    fun `take random N items`() {
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 1, 2, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        val randomList = list.takeRandomDistint(10)
        assert(randomList.size == 10)
        assert(randomList.distinctBy { it }.size == 10)
    }

    @Test
    fun `throw exception on N greater than size`() {
        val list = listOf(1, 2)
        val value = try {
            list.takeRandomDistint(3)
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
}