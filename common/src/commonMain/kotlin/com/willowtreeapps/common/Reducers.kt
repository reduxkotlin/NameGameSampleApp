package com.willowtreeapps.common

import com.beyondeye.reduks.ReducerFn
import com.willowtreeapps.common.Actions.*
import com.willowtreeapps.common.repo.Profile
import kotlin.math.abs
import kotlin.random.Random

/**
 * Reducers and functions used by reducers are in this file.  Functions must be pure functiuns without
 * side effects.
 */

val reducer = ReducerFn<AppState> { state, action ->
    when (action) {
        is FetchingProfilesStartedAction -> state.copy(isLoadingProfiles = true)
        is FetchingProfilesSuccessAction -> {
            val rounds = generateRounds(action.profiles, state.numRounds)
            state.copy(isLoadingProfiles = false, profiles = action.profiles, rounds = rounds)
        }
        is FetchingProfilesFailedAction -> state.copy(isLoadingProfiles = false, errorLoadingProfiles = true, errorMsg = action.message)
        else -> throw AssertionError("Action ${action::class.simpleName} not handled")
    }
}

fun generateRounds(profiles: List<Profile>, n: Int): List<Round> =
        profiles.takeRandomDistint(n)
                .map {
                    val choiceList = profiles.takeRandomDistint(3).toMutableList()
                    choiceList.add(abs(random.nextInt() % 4), it)

                    Round(profileId = ProfileId(it.id), choices = choiceList
                            .map { ProfileId(it.id) })
                }


private val random = Random(2340923874)

/**
 * Take N distict elements from the list.  Distict is determined by a comparasion of objects in the
 * list.
 * @throws IllegalStateException when n > number of distict elements.
 * @return New immutable list containing N random elements from the given List.
 */
fun <T> List<T>.takeRandomDistint(n: Int): List<T> {
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

