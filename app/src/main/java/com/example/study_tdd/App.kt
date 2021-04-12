package com.example.study_tdd

import android.app.Application
import com.example.study_tdd.macaddress.addressModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    addressModule
                )
            )
        }

    }
}


