package com.example.study_tdd.macaddress

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AlternativeMacAddressTest {

    companion object {
        private const val DUMMY_DATA = "125123"
    }

    @Mock
    lateinit var addressProvider: AddressProvider

    @Before
    fun setup() {
        addressProvider = Mockito.mock(AddressProvider::class.java)
    }

    @Test
    fun `대체맥어드레스 일시적인 데이터 값이 반환될때, 항상 값이 존재해야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TemporaryDateData())

        val alternativeMacAddress = AlternativeMacAddress(addressProvider)

        assertTrue(alternativeMacAddress.getAlternativeMacAddress().isNotEmpty())
    }


    @Test
    fun `대체맥어드레스 일시적인 데이터 값이 반환될때, 항상 값이 12자리이어야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TemporaryDateData())

        val alternativeMacAddress = AlternativeMacAddress(addressProvider)

        assertTrue(alternativeMacAddress.getAlternativeMacAddress().length == 12)
    }

    @Test
    fun `대체맥어드레스 일시적인 값이 아닐경우, 항상 값이 존재해야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TransformDigitAddress(DUMMY_DATA))

        val alternativeMacAddress = AlternativeMacAddress(addressProvider)

        assertTrue(alternativeMacAddress.getAlternativeMacAddress().isNotEmpty())
    }

}