package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

class SSAId(private val context: Context) : Address {

    private var _address = ""

    override val address: String
        get() = _address

    @SuppressLint("HardwareIds")
    override fun getAddress(): Boolean {
        return try {
            val getSSAId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

            if (getSSAId != null && getSSAId != "") {
                _address = getSSAId
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}