package com.example.study_tdd.macaddress

import android.media.MediaDrm
import android.os.Build
import android.util.Base64
import java.util.*


class WideVineId : Address {
    override fun getAddress(): String {
        return try {
            val mediaDrm = MediaDrm(UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L))
            val wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            val toEncoder =
                Base64.encodeToString(wideVineId, Base64.DEFAULT).trim()

            if (toEncoder.isNotEmpty()) {
                toEncoder
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}