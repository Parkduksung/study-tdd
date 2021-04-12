package com.example.study_tdd.macaddress

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TemporaryDateData : Address {
    @SuppressLint("SimpleDateFormat")
    override fun getAddress(): String {
        return SimpleDateFormat("yyMMddHHmmss").format(Date())
    }
}