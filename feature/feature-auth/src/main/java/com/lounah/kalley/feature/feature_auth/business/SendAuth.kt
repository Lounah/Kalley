package com.lounah.kalley.feature.feature_auth.business

import com.lounah.kalley.feature.feature_auth.domain.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.ui.signin.Credentials
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

internal class SendAuth(
    private val authSharedPrefs: AuthSharedPrefs
) : (Credentials) -> Completable {

    override fun invoke(credentials: Credentials): Completable =
        authSharedPrefs.saveDomain("sip@sip.info")
            .andThen(authSharedPrefs.saveUsername(credentials.first))
            .andThen(authSharedPrefs.savePassword(credentials.second))
            .delay(2, TimeUnit.SECONDS)
}