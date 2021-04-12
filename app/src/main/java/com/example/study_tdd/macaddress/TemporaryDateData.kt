package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TemporaryDateData : Address {

    private var _address = ""

    override val address: String
        get() = _address

    @SuppressLint("SimpleDateFormat")
    override fun getAddress(): Boolean {
        _address = SimpleDateFormat("yyMMddHHmmss").format(Date())
        return true
    }
}