package org.reduxkotlin.namegame.common.util

actual class PlatformLogger actual constructor() {
    actual var enabled: Boolean = true

    actual fun logDebug(msg: String) {
        if (enabled) {
            println(msg)
        }
    }

    actual fun logError(msg: String) {
        if (enabled) {
            println(msg)
        }
    }


}

