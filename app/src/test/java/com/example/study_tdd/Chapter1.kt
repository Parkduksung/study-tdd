package com.example.study_tdd

import com.example.study_tdd.chapter.Dollars
import com.example.study_tdd.chapter.Franc
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Test

class Chapter1 {

    private val dollars = Dollars(5)

    private val franc = Franc(5)

    @Test
    fun dollarExample() {
        assertEquals(10, dollars.times(2))
        assertEquals(15, dollars.times(3))
        // java 는 equals 가 없지만 kotlin 은 있음 구현 안해도 됨

        assertTrue(dollars.times(2) == franc.times(2))

        assertTrue(10.equals(dollars.times(2)))
        assertTrue(10 == dollars.times(2))

    }

}