package com.beyondeye.reduks

import com.beyondeye.reduks.middlewares.applyMiddleware

/**
 * Factory for some specific Store type
 * Created by daely on 7/31/2016.
 */
typealias StoreCreator<S> = (reducer: Reducer<S>, initialState: S) -> Store<S>

fun <S> StoreCreator<S>.enhancedWith(vararg enhancers: StoreEnhancer<S>): StoreCreator<S> {
    return combineEnhancers(*enhancers)(this)
}

fun <S> StoreCreator<S>.withMiddlewares(vararg middlewares: Middleware<S>): StoreCreator<S> = StoreCreatorWithMiddlewares(this, *middlewares)

fun <S> StoreCreatorWithMiddlewares(creator: StoreCreator<S>, vararg middlewares: Middleware<S>): StoreCreator<S> {
    return { reducer: Reducer<S>, initialState: S ->
        val res = creator(reducer, initialState)
        val store = res.applyMiddleware(*middlewares)
        store
    }
}

/**
 * create an enhanced store
 * extension method, so we save on method count
 */
fun <S> StoreCreator<S>.create(
        reducer: Reducer<S>,
        initialState: S,
        enhancer: StoreEnhancer<S>): Store<S> {
    return enhancer(this)(reducer, initialState)
}