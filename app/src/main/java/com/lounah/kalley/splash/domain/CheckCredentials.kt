package com.lounah.kalley.splash.domain

import com.lounah.kalley.core.storage.AuthSharedPrefs
import io.reactivex.Completable

internal class CheckCredentials(
    private val authSharedPrefs: AuthSharedPrefs
) : () -> Completable {

    override fun invoke(): Completable =
        if (authSharedPrefs.isAuthorized()) Completable.complete() else {
            Completable.error(IllegalAccessError())
        }

}