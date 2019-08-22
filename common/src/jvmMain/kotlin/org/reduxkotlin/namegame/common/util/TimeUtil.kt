package org.reduxkotlin.namegame.common.util

actual object TimeUtil {
    actual fun systemTimeMs(): Long = System.currentTimeMillis()
}