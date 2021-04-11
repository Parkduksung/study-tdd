package com.example.study_tdd.macaddress

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test


class NetworkUtilTest {


    @Test
    fun `일시적 날짜 데이터는 항상 12자리이어야 한다`() {

        val temporaryDateData = TemporaryDateData()

        assertTrue(temporaryDateData.getAddress())

        assertEquals(temporaryDateData.address.length, 12)

    }

}