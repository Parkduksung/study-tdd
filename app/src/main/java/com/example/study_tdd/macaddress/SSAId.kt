package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import android.app.Application
import android.provider.Settings

class SSAId(private val context: Application) : Address {
    override val address: String by lazy {
        try {
            val getSSAId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            if (getSSAId != null && getSSAId != "") {
                getSSAId
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    @SuppressLint("HardwareIds")
    override fun getAddress(): Boolean {
        return address.isNotEmpty()
    }
}