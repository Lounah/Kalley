package com.lounah.kalley.feature.feature_auth.di

import com.lounah.kalley.core.di.module
import com.lounah.kalley.feature.feature_auth.domain.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator
import com.lounah.kalley.feature.feature_auth.ui.signin.SignInPresenter

internal val authModule = module {
    single("usernameValidator") { UsernameValidator() as (String) -> Boolean }
    single("passwordValidator") { PasswordValidator() as (String) -> Boolean }
    single("authPrefs") { AuthSharedPrefs(context = get("appContext")) }
    factory { SignInPresenter(get("usernameValidator"), get("passwordValidator"), get("authPrefs")) }
}