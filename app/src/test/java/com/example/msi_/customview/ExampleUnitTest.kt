package com.example.msi_.customview

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testCos(){
        for (i in 0..6) {
            println(Math.cos(Math.toRadians(i*60.0)))
        }
    }
}
