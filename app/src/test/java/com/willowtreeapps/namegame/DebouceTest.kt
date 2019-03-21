package com.willowtreeapps.namegame

import com.willowtreeapps.common.util.debounce
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test

class DebounceTest {
    @Test
    fun `does not call if within delay time`() {
        var ct = 0
        val f: ()-> Unit = { ct++ }
        val debounceFun = debounce( f = f)
        debounceFun()
        debounceFun()
        assertEquals(1, ct)

    }

//    @Test
//    fun `handles multiple debounce without conflict`() {
//        fail()
//    }
}