package com.willowtreeapps.common.repo

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

expect fun userSettings(context: Any? = null): Settings

class LocalStorageSettingsRepository(private val settings: Settings) {

    fun saveNumRounds(numRounds: Int) {
        settings[NUM_ROUNDS] = numRounds
    }

    fun loadNumRounds(): Int = settings.getInt(NUM_ROUNDS, 4)

    companion object {
        const val NUM_ROUNDS = "NUM_ROUNDS"
    }
}