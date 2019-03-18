package com.willowtreeapps.namegame

import com.willowtreeapps.common.repo.KtorProfilesRepository
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val repo = KtorProfilesRepository()

    @Test
    fun fetchProfiles() {
        GlobalScope.launch {
            async {
                val result = repo.profiles()
                if (result.isSuccessful) {
                    assertNotNull(result.response)
                    assertTrue(result.response?.isNotEmpty() ?: false)
                } else {
                    fail("Failure response from profiles repo: ${result.message}")
                }
            }.await()
        }
        Thread.sleep(5000)
    }
}

