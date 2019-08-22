package org.reduxkotlin.namegame.common.middleware

import org.reduxkotlin.namegame.common.Actions
import org.reduxkotlin.namegame.common.AppState
import org.reduxkotlin.namegame.common.util.Logger
import org.reduxkotlin.namegame.common.util.TimeUtil
import kotlinx.coroutines.*
import org.reduxkotlin.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

val loggerMiddleware = middleware<AppState> { store, next, action ->
    val result = next(action)
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    result
}

class ThrottleMiddleware(backgroundContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext = backgroundContext + Job()

    private val map = mutableMapOf<KClass<*>, Pair<Job, Long>>()

    val throttleMiddleware = middleware<AppState> { store, next, action ->
        if (action is Actions.ThrottledAction) {

            val oldJob = map[action::class]?.first
            if (oldJob?.isActive == true) {
                oldJob.cancel()
            }

            val job = launch {
                withContext(this.coroutineContext) {
                    delay(action.waitTimeMs)
                }
                store.dispatch(action)
            }
            map[action::class] = Pair(job, TimeUtil.systemTimeMs())
            Unit
        } else {
            next(action)
        }

    }
}
