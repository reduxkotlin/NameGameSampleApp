package com.beyondeye.reduks

import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware

class SimpleStore<S>(initialState: S, private var reducer: Reducer<S>) : Store<S> {
    override fun replaceReducer(reducer: Reducer<S>) {
        this.reducer = reducer
        dispatch(INIT())
    }

    override var errorLogFn: ((String) -> Unit)? = null
    override var state: S = initialState
    private val subscribers = mutableListOf<StoreSubscriber>()
    private fun mainDispatcher(store: Store<S>, nextDispatcher: (Any) -> Any, action: Any): Any {
        //Todo consider how accessing from multiple threads affects this.
        try {
            state = reducer(store.state, action)
        } catch (e: Throwable) {
            ReduksInternalLogUtils.reportErrorInReducer(this@SimpleStore, e)
        }

        subscribers.forEach {
            try {
                it()
            } catch (e: Throwable) {
                ReduksInternalLogUtils.reportErrorInSubscriber(this@SimpleStore, e)
            }
        }
        return action
    }

    init {
        this.state = initialState
    }


    /**
     * dispach an action to the store and return it (eventually after it is transformed by middlewares)
     * An action can be of Any type
     */
    override var dispatch: (action: Any) -> Any = { action ->
        mainDispatcher(this,
                { it -> it }, //null dispatcher that ends the chain
                action)
    }

    override fun subscribe(storeSubscriber: StoreSubscriber): StoreSubscription {
        this.subscribers.add(storeSubscriber)
        return { subscribers.remove(storeSubscriber) }
    }

    companion object {
        val redukstag = "rdks"

        fun <S> create(withStandardMiddlewares: Boolean = true): StoreCreator<S> = { reducer, initialState ->
            val res = SimpleStore(initialState, reducer)
            if (!withStandardMiddlewares)
                res
            else
                res.applyMiddleware(::thunkMiddleware)
        }
    }
}

