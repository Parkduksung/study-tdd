package com.example.study_tdd.macaddress

import android.content.Context


class AlternativeMacAddress(private val addressProvider: AddressProvider) {

    fun getAlternativeMacAddress(): String {
        return addressProvider.create().getAddress()
    }
}