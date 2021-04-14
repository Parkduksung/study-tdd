package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

class SSAId(private val context: Context) : Address {
    @SuppressLint("HardwareIds")
    override fun getAddress(): String {
        return try {
            val getSSAId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            if (!getSSAId.isNullOrEmpty()) {
                getSSAId
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}