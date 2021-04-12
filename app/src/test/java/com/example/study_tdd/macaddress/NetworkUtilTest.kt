package com.example.study_tdd.macaddress

import android.app.Application
import android.content.Context
import android.media.MediaDrm
import android.os.Build
import android.provider.Settings
import android.util.Base64
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.annotation.Config
import java.util.*
@Config(
    application = TestApplication::class
)
@RunWith(MockitoJUnitRunner::class)
class NetworkUtilTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Mock
    lateinit var application: Application

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var temporaryDateData: Address

    @Mock
    lateinit var ssaId: Address

    @Mock
    lateinit var wideVineId: Address


    @Before
    fun setup() {

        temporaryDateData = TemporaryDateData()
    }


    @Test
    fun `일시적 날짜 데이터는 항상 12자리이어야 한다`() {

        assertTrue(temporaryDateData.getAddress())

        assertEquals(temporaryDateData.address.length, 12)

    }


    @Test
    fun `wideVineId 값이 정상적으로 획득되었을때, 값의 길이가 0 보다 길어야한다`() {



        SSAId(application).apply {
            getAddress()
            print(address)
        }

        if (wideVineId.getAddress()) {
            assertTrue(wideVineId.address.isNotEmpty())
        }
    }

    @Test
    fun `wideVineId 값이 정상적으로 획득되지 않았을때, 값의 길이가 0이다`() {

        val mediaDrm = MediaDrm(UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L))
        val wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
        val toEncoder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            java.util.Base64.getEncoder().encodeToString(wideVineId).trim()
        } else {
            Base64.encodeToString(wideVineId, Base64.DEFAULT).trim()
        }
//
//        WideVineId().apply {
//            hasAddress()
            print(toEncoder)
//        }


//        Mockito.`when`(wideVineId.hasAddress()).thenReturn(true)
//
//
//        print(wideVineId.address)


    }

    @Test
    fun `SSAId 값이 정상적으로 획득되었을때, address 값의 길이가 0보다 길어야한다`() {

        Mockito.`when`(ssaId.getAddress()).thenReturn(false)
//        Mockito.`when`(wideVineId.hasAddress()).thenReturn(false)

//
//        if (ssaId.hasAddress()) {
//            assertTrue(ssaId.address.isNotEmpty())
//        }

    }

    @Test
    fun `SSAId 값이 정상적으로 획득되지 않을때, 값의 길이가 0이다`() {
        print(Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID))

//        Mockito.`when`(ssaId.hasAddress()).thenReturn(false)
//
//        print(ssaId.address)

    }


}

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}