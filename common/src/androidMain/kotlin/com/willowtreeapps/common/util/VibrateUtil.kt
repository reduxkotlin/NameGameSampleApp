package com.willowtreeapps.common.util

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator


actual class VibrateUtil actual constructor(private val application: Any) {
    actual fun vibrate() {
        val v = (application as Application).getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v!!.vibrate(VibrationEffect.createOneShot(ANIM_DURATION, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v!!.vibrate(ANIM_DURATION)
        }
    }

    companion object {
        private const val ANIM_DURATION = 300L
    }
}