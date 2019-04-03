package com.willowtreeapps.namegame

import android.app.Application
import com.willowtreeapps.common.GameEngine
import kotlinx.coroutines.Dispatchers

class NameGameApp : Application() {

    lateinit var gameEngine: GameEngine

    override fun onCreate() {
        super.onCreate()
        instance = this
        val navigator = AndroidNavigator()
        gameEngine = GameEngine(navigator, this, Dispatchers.IO)

        registerActivityLifecycleCallbacks(navigator)
    }

    companion object {
        lateinit var instance: NameGameApp

        fun gameEngine() = instance.gameEngine
    }
}