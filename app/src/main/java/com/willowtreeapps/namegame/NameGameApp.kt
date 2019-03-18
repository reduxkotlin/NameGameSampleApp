package com.willowtreeapps.namegame

import android.app.Application
import com.willowtreeapps.common.Game
import com.willowtreeapps.common.Presenter
import kotlinx.coroutines.Dispatchers

class NameGameApp : Application() {

    lateinit var game: Game
    lateinit var navigator: AndroidNavigator
    lateinit var presenter: Presenter

    override fun onCreate() {
        super.onCreate()
        instance = this
        navigator = AndroidNavigator()
        game = Game(navigator)
        presenter = Presenter(game, Dispatchers.IO)

        registerActivityLifecycleCallbacks(navigator)
    }

    companion object {
        lateinit var instance: NameGameApp
    }
}