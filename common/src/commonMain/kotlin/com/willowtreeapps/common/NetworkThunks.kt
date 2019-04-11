package com.willowtreeapps.common

import com.beyondeye.reduks.*
import com.willowtreeapps.common.repo.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Thunks are functions that are executed by the "ThunkMiddleware".  They are asynchronous and dispatch
 * actions.  This allows dispatching a loading, success, and failure state.
 */
class NetworkThunks(private val networkContext: CoroutineContext,
                    val store: Store<AppState>) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = networkContext + job

    private val repo = CatItemRepository()

    fun fetchProfiles(): ThunkImpl<AppState> = ThunkFn { dispatcher, state ->
        Logger.d("Fetching StoreInfo and Feed")
        launch {
            store.dispatch(Actions.FetchingProfilesStartedAction())
            val result = repo.fetchItems()
            if (result.isSuccessful) {
                Logger.d("Success")
                store.dispatch(Actions.FetchingProfilesSuccessAction(result.response!!))
            } else {
                Logger.d("Failure")
                store.dispatch(Actions.FetchingProfilesFailedAction(result.message!!))
            }
            Logger.d("DONE")
        }
    }
}
