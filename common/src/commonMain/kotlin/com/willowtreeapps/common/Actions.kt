package com.willowtreeapps.common

import com.beyondeye.reduks.Action
import com.willowtreeapps.common.repo.ItemsHolder
import com.willowtreeapps.common.repo.Profile

sealed class Actions : Action {

    class FetchingItemsStartedAction
    data class FetchingItemsSuccessAction(val itemsHolder: ItemsHolder)
    data class FetchingItemsFailedAction(val message: String)

    data class NamePickedAction(val name: String)

    class NextQuestionAction

    class GameCompleteAction

    class StartOverAction
    class ResetGameStateAction

    data class StartQuestionTimerAction(val initialValue: Int)
    class DecrementCountDownAction
    class TimesUpAction


    class SettingsTappedAction
    class LoadAllSettingsAction
    data class SettingsLoadedAction(val settings: UserSettings)
    data class ChangeNumQuestionsSettingsAction(val num: Int)
    data class ChangeCategorySettingsAction(val categoryId: QuestionCategoryId)
    data class ChangeMicrophoneModeSettingsAction(val enabled: Boolean)

}

