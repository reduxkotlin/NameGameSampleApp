package com.willowtreeapps.namegame

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.Screen
import com.willowtreeapps.namegame.store.SettingsDialogFragment

/**
 * Android implementation of Navigator interface.  This will load the appropriate Activity or Fragment
 * using Android Navigation component.  Utilizes ActivityLifecycleCallbacks to keep reference of the current
 * Activity.
 */
class AndroidNavigator : Navigator, Application.ActivityLifecycleCallbacks {


    private var currentActivity: AppCompatActivity? = null
    private var cachedNavigationScreen: Screen? = null

    //TODO consider using current screen & destination screen to determine routing & animation
    override fun goto(screen: Screen) {
        if (currentActivity == null) {
            cachedNavigationScreen = screen
        } else {
            val navController = currentActivity!!.findNavController(R.id.nav_host_fragment)
            when (screen) {
                Screen.QUESTION -> navController.navigate(R.id.action_startScreen_to_questionScreen)
                Screen.GAME_COMPLETE -> navController.navigate(R.id.action_questionScreen_to_resultsFragment)
//            Screen.START -> navController.navigate(R.id.startScreen)
                Screen.START -> navController.navigate(R.id.action_resultsFragment_to_startScreen)
                Screen.SETTINGS -> {
                    val dialog = SettingsDialogFragment.newInstance()
                    dialog.show(currentActivity!!.supportFragmentManager, "SettingsFragment")
                }
                else -> throw IllegalArgumentException("Screen $screen is not handled in AndroidNavigator")
            }
        }
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
        if (cachedNavigationScreen!= null) {
            goto(cachedNavigationScreen!!)
            cachedNavigationScreen = null
        }
    }

    override fun onActivityStarted(activity: Activity?) {
        currentActivity = activity as AppCompatActivity?

    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
        currentActivity = null
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}