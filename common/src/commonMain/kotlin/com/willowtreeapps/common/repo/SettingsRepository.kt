package com.willowtreeapps.common.repo

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set
import com.willowtreeapps.common.QuestionCategoryId

expect fun userSettings(context: Any? = null): Settings

class LocalStorageSettingsRepository(private val settings: Settings) {

    var numRounds: Int
        get() = settings.getInt(NUM_ROUNDS, 4)
        set(numRounds) {
            settings[NUM_ROUNDS] = numRounds
        }

    var categoryId: QuestionCategoryId
        get() = QuestionCategoryId.valueOf(settings.getString(CATEGORY_ID, QuestionCategoryId.CATS.toString()))
        set(categoryId) {
            settings[CATEGORY_ID] = categoryId.toString()
        }

    companion object {
        private const val NUM_ROUNDS = "NUM_ROUNDS"
        private const val CATEGORY_ID = "CATEGORY_ID"
    }
}