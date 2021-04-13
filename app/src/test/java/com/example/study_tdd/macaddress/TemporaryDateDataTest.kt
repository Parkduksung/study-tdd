package com.example.study_tdd.macaddress

import org.junit.Assert.*
import org.junit.Test

class TemporaryDateDataTest {

    @Test
    fun `일시적인 날짜 데이터 값은 항상 12자리이어야 한다`() {

        val temporaryDateData = TemporaryDateData()

        assertTrue(temporaryDateData.getAddress().length == 12)

    }
}