package com.lounah.kalley.feature.feature_auth.di

import com.lounah.kalley.core.di.module
import com.lounah.kalley.core.storage.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator

internal val authModule = module {
    single { UsernameValidator() }
    single { PasswordValidator() }
    single { AuthSharedPrefs(context = get("appContext")) }
}