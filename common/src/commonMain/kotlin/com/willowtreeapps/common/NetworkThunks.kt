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
                    val engine: GameEngine) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = networkContext + job

    //TODO cache the repositories
    private fun repoForCategory(categoryId: QuestionCategoryId) = when (categoryId) {
        QuestionCategoryId.WILLOW_TREE -> ProfileItemRepository()
        QuestionCategoryId.CATS -> CatItemRepository()
        QuestionCategoryId.DOGS -> DogItemRepository()
    }

    fun fetchItems(categoryId: QuestionCategoryId): ThunkImpl<AppState> = ThunkFn { dispatcher, state ->
        val repo = repoForCategory(categoryId)
        Logger.d("Fetching StoreInfo and Feed")
        launch {
            engine.dispatch(Actions.FetchingItemsStartedAction())
            val result = repo.fetchItems(state.settings.numQuestions)
            if (result.isSuccessful) {
                Logger.d("Success")
                engine.dispatch(Actions.FetchingItemsSuccessAction(result.response!!))
            } else {
                Logger.d("Failure")
                engine.dispatch(Actions.FetchingItemsFailedAction(result.message!!))
            }
        }
    }
}
