package com.lounah.kalley.feature.feature_auth.ui

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.feature.feature_auth.R
import com.lounah.kalley.feature.feature_auth.di.authModule
import com.lounah.kalley.feature.feature_auth.ui.signin.SignInFragment

fun Context.startAuthActivity() =
    Intent(this, AuthActivity::class.java)
        .setFlags(FLAG_ACTIVITY_CLEAR_TASK)
        .setFlags(FLAG_ACTIVITY_NEW_TASK)
        .also(::startActivity)

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Di.loadModule(authModule)
        setContentView(R.layout.activity_auth)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.authContainer, SignInFragment())
            .commit()
    }
}
