@file:Suppress("CanBeParameter")

package com.lounah.kalley.feature.feature_auth.ui.signin

import com.lounah.kalley.core.architecture.redux.BaseReducer
import com.lounah.kalley.core.architecture.redux.ReduxPresenter
import com.lounah.kalley.core.architecture.redux.ReduxReducer
import com.lounah.kalley.core.architecture.redux.SideEff
import com.lounah.kalley.feature.feature_auth.domain.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.sideeff.SendAuthSideEff
import com.lounah.kalley.feature.feature_auth.ui.signin.sideeff.ValidateCredentialsSideEff

internal class SignInPresenter(
    private val usernameValidator: (String) -> Boolean,
    private val passwordValidator: (String) -> Boolean,
    private val authPrefs: AuthSharedPrefs
) : ReduxPresenter(AuthState()) {

    override val sideEffects: List<SideEff> = listOf(
        ValidateCredentialsSideEff(usernameValidator, passwordValidator),
        SendAuthSideEff(authPrefs)
    )

    override val reducer: ReduxReducer = BaseReducer<AuthState> { state, action ->
        when (action) {
            is SendAuthStarted -> state.copy(isLoading = true)
            else -> AuthState()
        }
    }
}