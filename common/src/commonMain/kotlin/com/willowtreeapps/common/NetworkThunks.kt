package com.willowtreeapps.common

import com.beyondeye.reduks.*
import com.willowtreeapps.common.repo.KtorStoreRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Thunks are functions that are executed by the "ThunkMiddleware".  They are asynchronous and dispatch
 * actions.  This allows dispatching a loading, success, and failure state.
 */
class NetworkThunks(private val uiContext: CoroutineContext) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = uiContext + job

    private val repo = KtorStoreRepository()

    fun fetchProfiles(): ThunkImpl<AppState> = ThunkFn<AppState> { dispatcher, state ->
        Logger.d("Fetching StoreInfo and Feed")
        launch {
            val result = repo.profiles()
            if (result.isSuccessful) {
                Logger.d("Success")
            } else {
                Logger.d("Failure")
            }
            Logger.d("DONE")
        }
    }
}
