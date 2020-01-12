package com.lounah.kalley.splash

import android.os.Bundle
import com.lounah.kalley.core.architecture.base.BaseActivity
import com.lounah.kalley.feature.feature_auth.ui.startAuthActivity
import com.lounah.kalley.splash.di.SplashComponent
import com.lounah.kalley.startMainActivity

internal class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = SplashComponent(
            navigateToMain = {
                startMainActivity()
                finish()
            },
            navigateToAuth = {
                startAuthActivity()
                finish()
            }
        )
        val events = SplashEventsImpl(unbindEvent, bindEvent)

        component.binder().bind(events)
    }
}