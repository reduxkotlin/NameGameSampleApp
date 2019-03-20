package com.willowtreeapps.namegame

import android.app.Application
import com.willowtreeapps.common.GameEngine
import com.willowtreeapps.common.PresenterFactory
import kotlinx.coroutines.Dispatchers

class NameGameApp : Application() {

    lateinit var gameEngine: GameEngine
    lateinit var navigator: AndroidNavigator
    lateinit var presenterFactory: PresenterFactory

    override fun onCreate() {
        super.onCreate()
        instance = this
        navigator = AndroidNavigator()
        gameEngine = GameEngine(navigator)
        presenterFactory = PresenterFactory(gameEngine, Dispatchers.IO)

        registerActivityLifecycleCallbacks(navigator)
    }

    companion object {
        lateinit var instance: NameGameApp
    }
}