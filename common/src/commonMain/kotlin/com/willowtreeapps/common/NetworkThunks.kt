package com.willowtreeapps.common

import com.willowtreeapps.common.repo.*
import kotlinx.coroutines.*
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.GetState
import org.reduxkotlin.Thunk
import org.reduxkotlin.createThunk
import kotlin.coroutines.CoroutineContext

/**
 * Thunks are functions that are executed by the "ThunkMiddleware".  They are asynchronous and dispatch
 * actions.  This allows dispatching a loading, success, and failure state.
 */
class NetworkThunks(private val networkContext: CoroutineContext) : CoroutineScope {
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = networkContext + job

    //TODO cache the repositories
    private fun repoForCategory(categoryId: QuestionCategoryId) = when (categoryId) {
        QuestionCategoryId.CATS -> CatItemRepository()
        QuestionCategoryId.DOGS -> DogItemRepository()
    }

    fun fetchItems(categoryId: QuestionCategoryId, numQuestions: Int) = thunk { dispatch, getState, extraArgument ->
        val repo = repoForCategory(categoryId)
        Logger.d("Fetching StoreInfo and Feed")
        launch {
            dispatch(Actions.FetchingItemsStartedAction())
            val result = repo.fetchItems(numQuestions)
            if (result.isSuccessful) {
                Logger.d("Success")
                dispatch(Actions.FetchingItemsSuccessAction(result.response!!))
            } else {
                Logger.d("Failure")
                dispatch(Actions.FetchingItemsFailedAction(result.message!!))
            }
        }
    }
}

/**
 * Convenience function so state type does is not needed every time a thunk is created.
 */
fun thunk(thunkLambda: (dispatch: Dispatcher, getState: GetState<AppState>, extraArgument: Any?) -> Any) =
        createThunk(thunkLambda)
