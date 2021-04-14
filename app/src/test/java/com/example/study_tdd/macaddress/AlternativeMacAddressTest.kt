package com.example.study_tdd.macaddress

import android.content.Context
import junit.framework.Assert.assertTrue
import org.junit.Assert
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
        private const val TWELVE_DIGIT = 12
        private const val EMPTY = ""
    }

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var ssaId: SSAId

    @Mock
    lateinit var wideVineId: WideVineId

    @Mock
    lateinit var addressProvider: AddressProvider

    @Before
    fun setup() {
        ssaId = Mockito.mock(SSAId::class.java)
        wideVineId = Mockito.mock(WideVineId::class.java)
        addressProvider = Mockito.mock(AddressProvider::class.java)
    }

    @Test
    fun `ssaId, wideVineId 값이 모두 "" 이 아닌 경우, TransformDigitAddress 를 반환한다`() {

        Mockito.`when`(ssaId.getAddress()).thenReturn("asdv1wdweh")
        Mockito.`when`(wideVineId.getAddress()).thenReturn("asdoij1[o2i")

        addressProvider = AddressProvider(ssaId, wideVineId)

        assertTrue(addressProvider.create() is TransformDigitAddress)
    }


    @Test
    fun `대체맥어드레스 일시적인 데이터 값이 반환될때, 항상 값이 존재해야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TemporaryDateData())

        val getAlternativeMacAddress = AlternativeMacAddress(addressProvider).getAlternativeMacAddress()

        assertTrue(getAlternativeMacAddress.isNotEmpty())

    }


    @Test
    fun `대체맥어드레스 일시적인 데이터 값이 반환될때, 항상 값이 12자리이어야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TemporaryDateData())

        val getAlternativeMacAddress = AlternativeMacAddress(addressProvider).getAlternativeMacAddress()

        assertTrue(getAlternativeMacAddress.length == TWELVE_DIGIT)
    }

    @Test
    fun `대체맥어드레스 일시적인 값이 아닐경우, 항상 값이 존재해야 한다`() {

        Mockito.`when`(addressProvider.create()).thenReturn(TransformDigitAddress(DUMMY_DATA))

        val getAlternativeMacAddress = AlternativeMacAddress(addressProvider).getAlternativeMacAddress()

        assertTrue(getAlternativeMacAddress.isNotEmpty())
    }


    @Test
    fun `ssaId, wideVineId 값이 모두 "" 인 경우, TemporaryDateData 를 반환한다`() {

        Mockito.`when`(ssaId.getAddress()).thenReturn(EMPTY)
        Mockito.`when`(wideVineId.getAddress()).thenReturn(EMPTY)

        addressProvider = AddressProvider(ssaId, wideVineId)

        assertTrue(addressProvider.create() is TemporaryDateData)
    }

    @Test
    fun `ssaId 값은 존재하고, wideVineId 값이 존재하지 않을 경우, TransformDigitAddress 를 반환한다`() {

        Mockito.`when`(ssaId.getAddress()).thenReturn("lkaviwen")
        Mockito.`when`(wideVineId.getAddress()).thenReturn(EMPTY)

        addressProvider = AddressProvider(ssaId, wideVineId)

        assertTrue(addressProvider.create() is TransformDigitAddress)
    }

    @Test
    fun `wideVineId 값은 존재하고, ssaId 값이 존재하지 않을 경우, TransformDigitAddress 를 반환한다`() {

        Mockito.`when`(ssaId.getAddress()).thenReturn(EMPTY)
        Mockito.`when`(wideVineId.getAddress()).thenReturn("qwopenobn;'")

        addressProvider = AddressProvider(ssaId, wideVineId)

        assertTrue(addressProvider.create() is TransformDigitAddress)
    }

    @Test
    fun `넘어온 값을 해시코드화 했을때, 문자열에 숫자만 있어야 한다`() {

        val convertHashCode = ConvertHashCode("adfevn")

        assertTrue(convertHashCode.getAddress().replace("[^0-9]", "").length == convertHashCode.getAddress().length)
    }


    @Test
    fun `일시적인 날짜 데이터 값은 항상 12자리이어야 한다`() {

        val temporaryDateData = TemporaryDateData()

        assertTrue(temporaryDateData.getAddress().length == TWELVE_DIGIT)

    }

    @Test
    fun `넘어온 값의 길이가 12자리 이상일 경우, 12자리까지만 반환해야 한다`() {

        val transformDigitAddress = TransformDigitAddress("11111111111123")

        assertTrue(transformDigitAddress.getAddress().length == TWELVE_DIGIT)

        junit.framework.Assert.assertEquals(transformDigitAddress.getAddress(), "111111111111")
    }

    @Test
    fun `넘어온 값의 길이가 12자리 미만일 경우, 12자리를 만들어 반환해야 한다`() {

        val transformDigitAddress = TransformDigitAddress("11111")

        assertTrue(transformDigitAddress.getAddress().length == TWELVE_DIGIT)

        junit.framework.Assert.assertEquals(transformDigitAddress.getAddress(), "111113440344")

    }

    @Test
    fun `wideVineId 값은 항상 null 이 아니어야 한다`() {

        wideVineId = WideVineId()
        assertTrue(wideVineId.getAddress() != null)
    }

    @Test
    fun `ssaId 값은 항상 null 이 아니어야 한다`() {

        ssaId = SSAId(context)
        assertTrue(ssaId.getAddress() != null)
    }
}