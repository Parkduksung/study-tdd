package com.example.study_tdd.macaddress

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val addressModule = module {
    single { SSAId(androidContext()) }
    single { WideVineId() }
    single { AddressProvider(get(), get()) }
    single { AlternativeMacAddress(get()) }
}