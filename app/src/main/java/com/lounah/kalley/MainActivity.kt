package com.lounah.kalley

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lounah.kalley.feature.calls.CallsFragment

internal fun Context.startMainActivity() =
    Intent(this, MainActivity::class.java)
        .setFlags(FLAG_ACTIVITY_CLEAR_TASK)
        .setFlags(FLAG_ACTIVITY_NEW_TASK)
        .also(::startActivity)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerMain, CallsFragment())
            .commit()
    }
}
