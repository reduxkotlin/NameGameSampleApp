package com.willowtreeapps.namegame

import com.willowtreeapps.common.repo.*
import com.willowtreeapps.common.repo.MockRepositoryFactory.Companion.VALID_RESPONSE_JSON
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import org.junit.Assert.*
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RepositoriesTest {
    private val repo = KtorProfilesRepository()

    @Test
    fun fetchProfiles() {
        val result = runBlocking { repo.profiles() }
        if (result.isSuccessful) {
            assertNotNull(result.response)
            assertTrue(result.response?.isNotEmpty() ?: false)
        } else {
            fail("Failure response from cats repo: ${result.message}")
        }
    }

    @Test
    fun deserializeProfilesResponse() {
        val response = Json.nonstrict.parse(ProfileListHolderSerializer(), MockRepositoryFactory.VALID_RESPONSE_JSON)
        assertNotNull(response.profiles)
    }

    @Test
    fun fetchDogs() {
        val result = runBlocking { repo.profiles() }
        if (result.isSuccessful) {
            assertNotNull(result.response)
            assertTrue(result.response?.isNotEmpty() ?: false)
        } else {
            fail("Failure response from cats repo: ${result.message}")
        }
    }
}

