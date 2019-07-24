package com.willowtreeapps.common.middleware

import com.willowtreeapps.common.Actions
import com.willowtreeapps.common.AppState
import com.willowtreeapps.common.Logger
import com.willowtreeapps.common.util.TimeUtil
import kotlinx.coroutines.*
import org.reduxkotlin.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

fun loggerMiddleware(getState: GetState, nextDispatcher: (Any) -> Any, action: Any): Any {
    val result = nextDispatcher(action)
    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    return result
}

val loggerMiddleware2: Middleware =
        { store ->
            { next ->
                { action ->
                    {
                        val result = next(action)
                        Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
                        result
                    }
                }
            }
        }

fun logMiddleware(store: Store) = { next: Dispatcher ->
    { action: Any ->
        next(action)
    }
}


val loggerMiddleware3 = middleware { store, next, action ->
//    Logger.d("DISPATCH action: ${action::class.simpleName}: $action")
    next(action)
}

class ThrottleMiddleware(backgroundContext: CoroutineContext) : CoroutineScope {
    override val coroutineContext = backgroundContext + Job()

    private val map = mutableMapOf<KClass<*>, Pair<Job, Long>>()

    val throttleMiddleware = middleware { store, next, action ->
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
