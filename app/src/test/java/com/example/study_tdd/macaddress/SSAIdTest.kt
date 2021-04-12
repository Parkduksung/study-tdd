package com.example.study_tdd.macaddress

import android.content.Context
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SSAIdTest {

    @Mock
    lateinit var context: Context

    @Test
    fun `ssaId 값은 항상 null 이 아니어야 한다`() {

        val ssaId = SSAId(context)

        assertTrue(ssaId.getAddress() != null)

    }
}