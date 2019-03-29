package com.willowtreeapps.common.util

import platform.AudioToolbox.AudioServicesPlayAlertSound
import platform.AudioToolbox.SystemSoundID
import platform.AudioToolbox.kSystemSoundID_Vibrate

actual class VibrateUtil actual constructor(private val application: Any) {
    actual fun vibrate() {
        AudioServicesPlayAlertSound(kSystemSoundID_Vibrate)

    }
}