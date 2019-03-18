package com.willowtreeapps.namegame

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.navigation.findNavController
import com.willowtreeapps.common.middleware.Navigator
import com.willowtreeapps.common.middleware.Screen

/**
 * Android implementation of Navigator interface.  This will load the appropriate Activity or Fragment
 * using Android Navigation component.  Utilizes ActivityLifecycleCallbacks to keep reference of the current
 * Activity.
 */
class AndroidNavigator : Navigator, Application.ActivityLifecycleCallbacks {


    var currentActivity: Activity? = null

    override fun goto(screen: Screen) {
        val navController = currentActivity!!.findNavController(R.id.nav_host_fragment)

        when (screen) {
            Screen.QUESTION -> navController.navigate(R.id.questionScreen)
            else -> throw IllegalArgumentException("Screen $screen is not handled in AndroidNavigator")
        }
    }

    override fun onActivityPaused(activity: Activity?) {
    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
        currentActivity = activity
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