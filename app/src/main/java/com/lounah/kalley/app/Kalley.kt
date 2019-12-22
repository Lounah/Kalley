package com.lounah.kalley.app

import android.app.Application
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.core.di.module

class Kalley : Application() {

    override fun onCreate() {
        super.onCreate()
        Di.loadModule(module {
            single("appContext") { applicationContext }
        })
    }
}