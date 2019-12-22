package com.lounah.kalley.feature.feature_auth.di

import com.lounah.kalley.core.di.module
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthPresenter

internal val authModule = module {
    single("usernameValidator") { UsernameValidator() as (String) -> Boolean }
    single("passwordValidator") { PasswordValidator() as (String) -> Boolean }
    factory { AuthPresenter() }
}