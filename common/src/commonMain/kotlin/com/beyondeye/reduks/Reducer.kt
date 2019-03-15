package com.beyondeye.reduks

/**
 * single method interface, mainly used because kotlin does not support yet type alias for function types
 * see also https://github.com/reactjs/redux/blob/master/docs/Glossary.md#reducer
 */

typealias Reducer<ReducerStateType> = (state: ReducerStateType, action: Any) -> ReducerStateType

