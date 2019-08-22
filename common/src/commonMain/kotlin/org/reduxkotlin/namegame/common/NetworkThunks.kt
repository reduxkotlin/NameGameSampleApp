package org.reduxkotlin.namegame.common

import org.reduxkotlin.namegame.common.util.Logger
import kotlinx.coroutines.*
import org.reduxkotlin.Dispatcher
import org.reduxkotlin.GetState
import org.reduxkotlin.createThunk
import org.reduxkotlin.namegame.common.repo.CatItemRepository
import org.reduxkotlin.namegame.common.repo.DogItemRepository
import kotlin.coroutines.CoroutineContext

/**
 * Thunks are functions that are executed by the "ThunkMiddleware".  They are asynchronous and dispatch
 * actions.  This allows dispatching a loading, success, and failure state.
 */
class NetworkThunks(networkContext: CoroutineContext) {
    private val networkScope = CoroutineScope(networkContext)

    //TODO cache the repositories
    private fun repoForCategory(categoryId: QuestionCategoryId) = when (categoryId) {
        QuestionCategoryId.CATS -> CatItemRepository()
        QuestionCategoryId.DOGS -> DogItemRepository()
    }

    fun fetchItems(categoryId: QuestionCategoryId, numQuestions: Int) = thunk { dispatch, getState, extraArgument ->
        val repo = repoForCategory(categoryId)
        Logger.d("Fetching StoreInfo and Feed")
        networkScope.launch {
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
