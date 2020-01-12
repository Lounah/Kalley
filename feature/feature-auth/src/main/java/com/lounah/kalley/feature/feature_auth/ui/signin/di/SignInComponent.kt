package com.lounah.kalley.feature.feature_auth.ui.signin.di

import com.lounah.kalley.core.di.Di
import com.lounah.kalley.feature.feature_auth.business.SendAuth
import com.lounah.kalley.feature.feature_auth.domain.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator
import com.lounah.kalley.feature.feature_auth.ui.signin.SendAuthActionCreator
import com.lounah.kalley.feature.feature_auth.ui.signin.SignInEventsBinder
import com.lounah.kalley.feature.feature_auth.ui.signin.VerifyCredentialsActionCreator
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionMapper
import ru.tinkoff.eba.actioncreators.toActionCreator

internal class SignInComponent(
    private val changeSignInState: ActionMapper<Boolean>,
    private val showLoading: Action,
    private val showError: Action,
    private val onAuthSucceed: Action,
    private val closeAuth: Action
) {

    fun binder(): SignInEventsBinder {
        val usernameValidator = Di.get<UsernameValidator>()
        val passwordValidator = Di.get<PasswordValidator>()
        val authSharedPrefs = Di.get<AuthSharedPrefs>()
        val verifyCredentials =
            VerifyCredentialsActionCreator(changeSignInState, usernameValidator, passwordValidator)
        val sendAuth = SendAuth(authSharedPrefs)
        val sendAuthActionCreator =
            SendAuthActionCreator(sendAuth, onAuthSucceed, showLoading, showError)
        val closeAuthActionCreator = closeAuth.toActionCreator()
        return SignInEventsBinder(verifyCredentials, sendAuthActionCreator, closeAuthActionCreator)
    }
}