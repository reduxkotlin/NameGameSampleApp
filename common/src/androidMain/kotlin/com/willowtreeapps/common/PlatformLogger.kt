package com.willowtreeapps.common

import java.util.logging.Level
import java.util.logging.Logger

actual class PlatformLogger actual constructor() {
    actual var enabled = true

    actual fun logDebug(msg: String) {
        if (enabled)
            Logger.getAnonymousLogger().log(Level.INFO, msg)
    }

    actual fun logError(msg: String) {
        if (enabled)
            Logger.getAnonymousLogger().log(Level.INFO, msg)
    }

}