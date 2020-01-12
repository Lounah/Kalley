package com.lounah.kalley.app

import android.app.Application
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.core.di.module
import com.lounah.kalley.core.storage.AuthSharedPrefs

@Suppress("unused")
class Kalley : Application() {

    override fun onCreate() {
        super.onCreate()
        Di.loadModule(module {
            single("appContext") { applicationContext }
            single { AuthSharedPrefs(context = get("appContext")) }
        })
    }
}