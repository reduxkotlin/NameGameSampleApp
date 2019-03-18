package com.willowtreeapps.common

import com.beyondeye.reduks.ReducerFn
import com.willowtreeapps.common.Actions.*

val reducer = ReducerFn<AppState> { state, action ->
    when (action) {
        is FetchingProfilesStartedAction -> state.copy(isLoadingProfiles = true)
        is FetchingProfilesSuccessAction -> state.copy(isLoadingProfiles = false, profiles = action.profiles)
        is FetchingProfilesFailedAction -> state.copy(isLoadingProfiles = false, errorLoadingProfiles = true, errorMsg = action.message)
        else -> throw AssertionError("Action ${action::class.simpleName} not handled")
    }
}

