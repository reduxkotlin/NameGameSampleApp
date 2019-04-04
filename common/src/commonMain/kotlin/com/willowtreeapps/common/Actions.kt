package com.willowtreeapps.common

import com.beyondeye.reduks.Action
import com.willowtreeapps.common.repo.Profile

sealed class Actions : Action {

    class GameStartedAction()

    class FetchingProfilesStartedAction
    class FetchingProfilesSuccessAction(val profiles: List<Profile>)
    class FetchingProfilesFailedAction(val message: String)

    class NamePickedAction(val name: String)

    class NextQuestionAction

    class GameCompleteAction

    class StartOverAction
    class ResetGameStateAction

    class StartQuestionTimerAction(val initialValue: Int)

    class StartTimerAction(val name: String,
                           val intervalMs: Long,
                           val initialValue: Int = 5,
                           val work: () -> Unit)

    class StopTimerAction

    class DecrementCountDownAction
    class TimesUpAction

}

