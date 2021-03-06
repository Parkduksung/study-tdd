package com.example.study_tdd.macaddress


class AlternativeMacAddress(private val addressProvider: AddressProvider) {
    fun getAlternativeMacAddress(): String {
        return addressProvider.create().getAddress()
    }
}