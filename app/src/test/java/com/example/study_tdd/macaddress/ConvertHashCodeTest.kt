package com.example.study_tdd.macaddress

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ConvertHashCodeTest {

    @Test
    fun `넘어온 값이 "" 일 경우, "" 으로 반환해야 한다`() {

        val convertHashCode = ConvertHashCode("")

        assertTrue(convertHashCode.getAddress().isEmpty())
        assertEquals(convertHashCode.getAddress(), "")
    }

    @Test
    fun `넘어온 값을 해시코드화 했을때, 문자열에 숫자만 있어야 한다`() {
        val convertHashCode = ConvertHashCode("adfevn")

        assertTrue(
            convertHashCode.getAddress()
                .replace("[^0-9]", "").length == convertHashCode.getAddress().length
        )
    }

}