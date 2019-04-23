package com.willowtreeapps.namegame

import com.willowtreeapps.common.util.debounce
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class DebounceTest {
    @Test
    fun `does not call if within delay time`() {
        var ct = 0
        runBlocking {
            val f: (Unit) -> Unit = { ct++ }
            val debounceFun = debounce(coroutineContext = this.coroutineContext, f = f)
            debounceFun(Unit)
            debounceFun(Unit)
            debounceFun(Unit)
            debounceFun(Unit)
            debounceFun(Unit)
            debounceFun(Unit)
        }
        assertEquals(1, ct)
    }

//    @Test
//    fun `handles multiple debounce without conflict`() {
//        fail()
//    }
}