package com.beyondeye.reduks

/**
 * get a store creator and return a new enhanced one
 * see https://github.com/reactjs/redux/blob/master/docs/Glossary.md#store-enhancer
 */
typealias StoreEnhancer<S> = (next: StoreCreator<S>) -> StoreCreator<S>

