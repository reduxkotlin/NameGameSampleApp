package com.beyondeye.reduks

/**
 * see also https://github.com/reactjs/redux/blob/master/docs/Glossary.md#reducer
 */

typealias Reducer<ReducerStateType> = (state: ReducerStateType, action: Any) -> ReducerStateType

