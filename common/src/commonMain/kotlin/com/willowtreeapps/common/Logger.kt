package com.willowtreeapps.common

expect class PlatformLogger() {
    var enabled: Boolean

    fun logDebug(msg: String)
    fun logError(msg: String)
}


object Logger {
    private val platformLogger = PlatformLogger()

    var enabled
        get() = platformLogger.enabled
        set(value) {
            platformLogger.enabled = value
        }

    fun d(message: String){
        platformLogger.logDebug(message)
    }

    fun e(message: String, exception: Throwable? = null){
        exception?.let {
            platformLogger.logError(message)
        } ?: run {
            platformLogger.logError(message)
        }
    }
}