package com.lounah.kalley.feature.feature_auth.ui.signin

import com.lounah.kalley.core.architecture.redux.BaseReducer
import com.lounah.kalley.core.architecture.redux.ReduxPresenter
import com.lounah.kalley.core.architecture.redux.ReduxReducer
import com.lounah.kalley.core.architecture.redux.SideEff
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.sideeff.SendAuthSideEff
import com.lounah.kalley.feature.feature_auth.ui.signin.sideeff.ValidateCredentialsSideEff

internal class SignInPresenter(
    private val usernameValidator: (String) -> Boolean,
    private val passwordValidator: (String) -> Boolean
) : ReduxPresenter(AuthState()) {

    override val sideEffects: List<SideEff> get() = listOf(
        SendAuthSideEff(),
        ValidateCredentialsSideEff(usernameValidator, passwordValidator)
    )

    override val reducer: ReduxReducer get() = BaseReducer<AuthState> { state, action ->
        when (action) {
            is SendAuth -> state.copy(isLoading = true)
            else -> state
        }
    }
}