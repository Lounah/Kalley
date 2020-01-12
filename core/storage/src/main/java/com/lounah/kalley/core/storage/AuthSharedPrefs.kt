package com.lounah.kalley.core.storage

import android.content.Context
import io.reactivex.Completable
import io.reactivex.Completable.*

private const val PREF_USERNAME = "pref_username"
private const val PREF_PASSWORD = "pref_password"
private const val PREF_DOMAIN = "pref_domain"

class AuthSharedPrefs(
    override val name: String = "AuthSharedPrefs",
    context: Context
) : AbsSharedPrefs(context) {

    fun saveDomain(domain: String): Completable =
        fromAction { putString(PREF_DOMAIN, domain) }
    fun saveUsername(username: String): Completable =
        fromAction { putString(PREF_USERNAME, username) }
    fun savePassword(password: String): Completable =
        fromAction { putString(PREF_PASSWORD, password) }

    fun getUsername() = getString(PREF_USERNAME)
    fun getPassword() = getString(PREF_PASSWORD)
    fun getDomain() = getString(PREF_DOMAIN)
}