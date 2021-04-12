package com.example.study_tdd.macaddress

import org.junit.Assert
import org.junit.Test

class WideVineIdTest{

    @Test
    fun `wideVineId 값은 항상 null 이 아니어야 한다`() {

        val wideVineId = WideVineId()

        Assert.assertTrue(wideVineId.getAddress() != null)

    }
}