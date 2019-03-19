package com.willowtreeapps.common

import com.beyondeye.reduks.SimpleStore
import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware
import com.willowtreeapps.common.middleware.NavigationMiddleware
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.ViewEffectsMiddleware
import com.willowtreeapps.common.repo.Profile
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringSerializer

data class AppState(val isLoadingProfiles: Boolean,
                    val profiles: List<Profile> = listOf(),
                    val errorLoadingProfiles: Boolean = false,
                    val errorMsg: String = "",
                    val currentRoundIndex: Int = 0,
                    val numRounds: Int = 10,
                    val rounds: List<Round> = listOf()) {
    companion object {
        val INITIAL_STATE = AppState(false)
    }

    fun Round.profile(): Profile = profiles.find { ProfileId(it.id) == this.profileId }!!

    val currentRound: Round
        get() = rounds[currentRoundIndex]

    fun getProfile(id: ProfileId) = profiles.find { it.id == id.id }

}

inline class ProfileId(val id: String)

data class Round(val profileId: ProfileId, val choices: List<ProfileId>, val answerProfileId: ProfileId? = null)

class Game(navigator: Navigator) {
    val navigationMiddleware = NavigationMiddleware(navigator)
    val viewEffectsMiddleware = ViewEffectsMiddleware()
    val appStore by lazy {
        SimpleStore(AppState.INITIAL_STATE, reducer)
                .applyMiddleware(::thunkMiddleware,
                        viewEffectsMiddleware::dispatch,
                        navigationMiddleware::dispatch)
    }
}

