package com.willowtreeapps.namegame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.content.res.Resources

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

/*
 * Convenience function for executing lambda after animation set is finished.
 */
fun AnimatorSet.onComplete(after: () -> Unit) {
        this.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                after()
            }
        })
    }