package com.beyondeye.reduks

/**
 * see also https://github.com/reactjs/redux/blob/master/docs/Glossary.md#middleware
 */

typealias Middleware<State> = (store: Store<State>, nextDispatcher: (Any) -> Any, action: Any) -> Any
