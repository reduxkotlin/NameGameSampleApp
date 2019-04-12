package com.willowtreeapps.common.util

import com.willowtreeapps.common.Logger

inline fun <T> profile(label: String, f: () -> T): T {
    val startMs = TimeUtil.systemTimeMs()
    val result = f()
    Logger.d("$label: ${TimeUtil.systemTimeMs() - startMs}ms")
    return result
}