package com.example.study_tdd.macaddress

import android.media.MediaDrm
import android.os.Build
import android.util.Base64
import java.util.*


class WideVineId : Address {

    private var _address = ""

    override val address: String
        get() = _address

    override fun getAddress(): Boolean {
        return try {
            val mediaDrm = MediaDrm(UUID(-0x121074568629b532L, -0x5c37d8232ae2de13L))
            val wideVineId = mediaDrm.getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            val toEncoder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                java.util.Base64.getEncoder().encodeToString(wideVineId).trim()
            } else {
                Base64.encodeToString(wideVineId, Base64.DEFAULT).trim()
            }

            if (toEncoder != "") {
                _address = toEncoder
                true
            } else {
                _address = ""
                false
            }
        } catch (e: Exception) {
            _address = ""
            false
        }
    }

    fun hasAddress1() : Boolean {
        return address!=""
    }
}