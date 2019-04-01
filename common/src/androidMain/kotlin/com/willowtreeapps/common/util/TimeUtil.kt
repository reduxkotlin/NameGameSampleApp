package com.willowtreeapps.common.util

actual object TimeUtil {
    actual fun systemTimeMs(): Long = System.currentTimeMillis()
}