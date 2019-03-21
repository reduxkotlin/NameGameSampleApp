package com.willowtreeapps.common.util


/*
private val debounceMap = mutableMapOf<(() -> Unit), Long>()

//experimental
fun debounce(delay: Long = 500L, f: () -> Unit): () -> Unit {
    return {
        val lastInvoke = debounceMap[f]
        val currentTimeMs = DateTime.nowUnixLong()
        if (lastInvoke == null || currentTimeMs - lastInvoke > delay) {
            debounceMap[f] = currentTimeMs
            f()
        }
    }
}
*/
