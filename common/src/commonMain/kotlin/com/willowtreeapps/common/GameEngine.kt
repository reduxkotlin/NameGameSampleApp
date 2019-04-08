package com.willowtreeapps.common

import com.beyondeye.reduks.SimpleStore
import com.beyondeye.reduks.middlewares.applyMiddleware
import com.beyondeye.reduks.middlewares.thunkMiddleware
import com.willowtreeapps.common.middleware.NavigationMiddleware
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.ViewEffectsMiddleware
import com.willowtreeapps.common.util.VibrateUtil
import kotlin.coroutines.CoroutineContext

class GameEngine(navigator: Navigator, application: Any = Any(),
                 networkContext: CoroutineContext,
                 uiContext: CoroutineContext) {
    private val navigationMiddleware = NavigationMiddleware(navigator)
    private val viewEffectsMiddleware = ViewEffectsMiddleware()
    private val presenterFactory by lazy { PresenterFactory(this, networkContext) }
    val vibrateUtil = VibrateUtil(application)

    val appStore by lazy {
        SimpleStore(AppState.INITIAL_STATE, ::reducer)
                .applyMiddleware(::thunkMiddleware,
                        viewEffectsMiddleware::dispatch,
                        navigationMiddleware::dispatch)
    }

    fun attachView(view: View): Presenter<out View?> = presenterFactory.attachView(view)

    fun detachView(view: View) = presenterFactory.detachView(view)
}