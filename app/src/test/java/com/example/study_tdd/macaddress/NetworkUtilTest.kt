package com.example.study_tdd.macaddress

import android.content.Context
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUtilTest {

    @Mock
    lateinit var context: Context


    @Test
    fun `일시적 날짜 데이터는 항상 12자리이어야 한다`() {

        val temporaryDateData = TemporaryDateData()

        assertTrue(temporaryDateData.getAddress())

        assertEquals(temporaryDateData.address.length, 12)

    }


    @Test
    fun `wideVineId 값이 정상적으로 획득되었을때, 값의 길이가 0 보다 길어야한다`() {

        val wideVineId = WideVineId()

        if (wideVineId.getAddress()) {
            assertTrue(wideVineId.address.isNotEmpty())
        }
    }

    @Test
    fun `wideVineId 값이 정상적으로 획득되지 않았을때, 값의 길이가 0이다`() {

        val wideVineId = WideVineId()

        if (!wideVineId.getAddress()) {
            assertEquals(wideVineId.address.length, 0)
        }
    }

    @Test
    fun `SSAId 값이 정상적으로 획득되었을때, 값의 길이가 0한 보다 길어야한다`() {

        val ssaId = SSAId(context)

        if (ssaId.getAddress()) {
            assertTrue(ssaId.address.isNotEmpty())
        }
    }

    @Test
    fun `SSAId 값이 정상적으로 획득되지 않을때, 값의 길이가 0이다`() {

        val ssaId = SSAId(context)

        if (!ssaId.getAddress()) {
            assertEquals(ssaId.address.length, 0)
        }
    }

}