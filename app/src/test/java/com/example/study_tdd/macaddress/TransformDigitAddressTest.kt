package com.example.study_tdd.macaddress

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class TransformDigitAddressTest {

    @Test
    fun `넘어온 값의 길이가 12자리 이상일 경우, 12자리까지만 반환해야 한다`() {

        val transformDigitAddress = TransformDigitAddress("11111111111123")

        assertTrue(transformDigitAddress.getAddress().length == 12)

        assertEquals(transformDigitAddress.getAddress(), "111111111111")
    }

    @Test
    fun `넘어온 값의 길이가 12자리 미만일 경우, 12자리를 만들어 반환해야 한다`() {

        val transformDigitAddress = TransformDigitAddress("11111")

        assertTrue(transformDigitAddress.getAddress().length == 12)

        assertEquals(transformDigitAddress.getAddress(), "111113440344")

    }
}