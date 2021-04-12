package com.example.study_tdd.macaddress

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class AddressProviderTest {

    @Mock
    lateinit var ssaId: SSAId

    @Mock
    lateinit var wideVineId: WideVineId


    @Before
    fun setup() {

        ssaId = Mockito.mock(SSAId::class.java)
        wideVineId = Mockito.mock(WideVineId::class.java)

    }

    @Test
    fun `ssaId, wideVineId 값이 모두 "" 인 경우, TemporaryDateData 를 반환한다`() {


        Mockito.`when`(ssaId.getAddress()).thenReturn("")
        Mockito.`when`(wideVineId.getAddress()).thenReturn("")
        val addressProvider2 = AddressProvider(ssaId, wideVineId)

        print(addressProvider2.create().toString())

        assertTrue(addressProvider2.create() is TemporaryDateData)

    }

    @Test
    fun `ssaId, wideVineId 값이 모두 "" 이 아닌 경우, TransformDigitAddress 를 반환한다`() {


        Mockito.`when`(ssaId.getAddress()).thenReturn("asdv1wdweh")
        Mockito.`when`(wideVineId.getAddress()).thenReturn("asdoij1[o2i")
        val addressProvider2 = AddressProvider(ssaId, wideVineId)

        print(addressProvider2.create().toString())

        assertTrue(addressProvider2.create() is TransformDigitAddress)

    }

    @Test
    fun `ssaId 값은 존재하고, wideVineId 값이 존재하지 않을 경우, TransformDigitAddress 를 반환한다`() {


        Mockito.`when`(ssaId.getAddress()).thenReturn("lkaviwen")
        Mockito.`when`(wideVineId.getAddress()).thenReturn("")
        val addressProvider2 = AddressProvider(ssaId, wideVineId)

        print(addressProvider2.create().toString())

        assertTrue(addressProvider2.create() is TransformDigitAddress)

    }

    @Test
    fun `wideVineId 값은 존재하고, ssaId 값이 존재하지 않을 경우, TransformDigitAddress 를 반환한다`() {


        Mockito.`when`(ssaId.getAddress()).thenReturn("")
        Mockito.`when`(wideVineId.getAddress()).thenReturn("qwopenobn;'")
        val addressProvider2 = AddressProvider(ssaId, wideVineId)

        print(addressProvider2.create().toString())

        assertTrue(addressProvider2.create() is TransformDigitAddress)

    }

}