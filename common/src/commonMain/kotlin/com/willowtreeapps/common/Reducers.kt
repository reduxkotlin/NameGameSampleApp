package com.willowtreeapps.common

import com.beyondeye.reduks.ReducerFn
import com.willowtreeapps.common.Actions.*

val reducer = ReducerFn<AppState> { state, action ->
    when (action) {

        else -> throw AssertionError("Action ${action::class.simpleName} not handled")
    }
}

