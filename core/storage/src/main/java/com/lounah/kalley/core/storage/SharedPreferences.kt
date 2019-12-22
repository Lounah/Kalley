package com.lounah.kalley.core.storage

import android.content.Context
import android.content.SharedPreferences

interface SharedPrefs {

    fun getString(key: String): String?
    fun getInt(key: String): Int?
    fun getBoolean(key: String): Boolean?
    fun putString(key: String, value: String)
    fun putInt(key: String, value: Int)
    fun putBoolean(key: String, value: Boolean)
}

abstract class AbsSharedPrefs(private val context: Context) : SharedPrefs {
    abstract val name: String

    override fun getString(key: String): String?
            = getPrefs().getString(key, null)

    override fun getInt(key: String): Int?
            = getPrefs().getInt(key, -1)

    override fun getBoolean(key: String): Boolean?
            = getPrefs().getBoolean(key, false)

    override fun putBoolean(key: String, value: Boolean) {
        getPrefs().edit()
                .putBoolean(key, value)
                .apply()
    }

    override fun putInt(key: String, value: Int) {
        getPrefs().edit()
                .putInt(key, value)
                .apply()
    }

    override fun putString(key: String, value: String) {
        getPrefs().edit()
                .putString(key, value)
                .apply()
    }

    private fun getPrefs(): SharedPreferences =
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
}