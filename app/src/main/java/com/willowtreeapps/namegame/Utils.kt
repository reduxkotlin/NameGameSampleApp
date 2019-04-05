package com.willowtreeapps.namegame

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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

/*
 * Convenience function for executing lambda after the image is displayed
 */
fun <T: Any> GlideRequest<T>.onComplete(after: () -> Unit): GlideRequest<T> {
    return this.listener(object : RequestListener<T> {
        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<T>?, isFirstResource: Boolean): Boolean {
            return true
        }
        override fun onResourceReady(resource: T?, model: Any?, target: Target<T>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            after()
            return false
        }

    })
}