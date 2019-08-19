package com.willowtreeapps.namegame

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.willowtreeapps.common.GameEngine
import com.willowtreeapps.common.Logger
import kotlinx.coroutines.Dispatchers
import org.reduxkotlin.Dispatcher

lateinit var dispatch: Dispatcher

class NameGameApp : Application() {

    lateinit var gameEngine: GameEngine

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        instance = this
        val navigator = AndroidNavigator()
        gameEngine = GameEngine(navigator, this, Dispatchers.IO, Dispatchers.Main)
        dispatch = gameEngine.appStore.dispatch

        registerActivityLifecycleCallbacks(navigator)
        registerActivityLifecycleCallbacks(LifeCycleLogger)
    }

    companion object {
        lateinit var instance: NameGameApp

        fun gameEngine() = instance.gameEngine
    }
}

object LifeCycleLogger: Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityPaused")
    }

    override fun onActivityResumed(activity: Activity?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityResumed")
    }

    override fun onActivityStarted(activity: Activity?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityStarted")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityDestroyed")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivitySaveInstanceState")
    }

    override fun onActivityStopped(activity: Activity?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityStopped")
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Logger.d(activity?.javaClass?.simpleName + ": onActivityCreated")
    }

}