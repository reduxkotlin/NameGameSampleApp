package com.willowtreeapps.common

import com.beyondeye.reduks.Action
import com.willowtreeapps.common.repo.Profile

sealed class Actions : Action {

    class FetchingProfilesStartedAction
    class FetchingProfilesSuccessAction(val profiles: List<Profile>)
    class FetchingProfilesFailedAction(val message: String)

    class NamePickedAction(val name: String)

    class NextQuestionAction

    class GameCompleteAction

    class StartOverAction
    class ResetGameStateAction

    class StartQuestionTimerAction(val initialValue: Int)
    class DecrementCountDownAction
    class TimesUpAction

    class LoadAllSettingsAction
    class ChangeNumQuestionsSettingsAction(val num: Int)

}

