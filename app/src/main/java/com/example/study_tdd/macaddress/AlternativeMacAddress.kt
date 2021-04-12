package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaDrm
import android.os.Build
import android.provider.Settings
import android.util.Base64
import java.text.SimpleDateFormat
import java.util.*


class AlternativeMacAddress {

    fun getAlternativeMacAddress(context: Context): String? {
        val wideVineId = getWideVineId()
        val ssaId = getSSAId(context)
        val combineWideVineAndSSAId = wideVineId + ssaId
        return if (combineWideVineAndSSAId.isEmpty()) {
            getTemporaryDateData()
        } else {
            convertHashCode(combineWideVineAndSSAId)
        }
    }

    @SuppressLint("HardwareIds")
    private fun getSSAId(context: Context): String {
        return try {
            val getSSAId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            getSSAId ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    private fun getWideVineId(): String {
        return try {
            val mediaDrm = MediaDrm(UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L))
            val wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                java.util.Base64.getEncoder().encodeToString(wideVineId).trim { it <= ' ' }
            } else {
                Base64.encodeToString(wideVineId, Base64.DEFAULT).trim { it <= ' ' }
            }
        } catch (e: Exception) {
            ""
        }
    }

    private fun convertHashCode(combineWideVineAndSSAId: String): String? {
        val convertHashCode = StringBuilder(Math.abs(combineWideVineAndSSAId.hashCode()).toString())
        val addArray = arrayOf("4", "4", "3", "0")

        // 12자리 숫자를 만들어 주기 위한 로직.
        return if (convertHashCode.length < 12) {
            val convertHashCodeLength = convertHashCode.length
            // 12자리 숫자가 아닌경우 빈공간에 순차적으로 배열의 값을 추가하여 12자리를 만들어준다.
            for (i in convertHashCodeLength..11) {
                convertHashCode.append(addArray[(12 - i) % 4])
            }
            convertHashCode.toString()
        } else {

            // 12자리 숫자 초과인 경우 12자리까지 자른다.
            convertHashCode.substring(0, 11)
        }
    }

    // wideVineId, SSAId 값이 모두 "" 인 경우, 년월일시분초 임시데이터를 만들어 저장 (IOS 방식과 동일)
    @SuppressLint("SimpleDateFormat")
    private fun getTemporaryDateData(): String? {
        return SimpleDateFormat("yyMMddHHmmss").format(Date())
    }

}