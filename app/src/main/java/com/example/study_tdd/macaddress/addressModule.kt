package com.example.study_tdd.macaddress

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val addressModule = module {
    single { AlternativeMacAddress(AddressProvider(SSAId(androidContext()), WideVineId())) }
}