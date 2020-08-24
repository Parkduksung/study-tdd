package com.example.study_tdd

import com.example.study_tdd.chapter.Dollars
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Test

class Chapter1 {

    private val dollars = Dollars(5)

    @Test
    fun dollarExample() {
        assertEquals(10, dollars.times(2))
        assertEquals(15, dollars.times(3))
    }

}