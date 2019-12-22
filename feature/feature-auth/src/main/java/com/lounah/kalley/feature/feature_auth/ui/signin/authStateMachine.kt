@file:Suppress("CanSealedSubClassBeObject")

package com.lounah.kalley.feature.feature_auth.ui.signin

import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxEffect
import com.lounah.kalley.core.architecture.redux.ReduxState

internal sealed class AuthAction : ReduxAction {
    class OnSignInClicked : AuthAction()
    class SendAuth : AuthAction()
    data class OnUsernameChanged(val username: String) : AuthAction()
    data class OnPasswordChanged(val password: String) : AuthAction()
}

internal sealed class AuthEffect : ReduxEffect {
    class ShowAuthError(val message: String) : AuthEffect()
    class OnAuthSuccess : AuthEffect()
    class ProceedAuthButtonStateChanged(val enabled: Boolean) : AuthEffect()
}

internal data class AuthState(
    val usernameHasErrors: Boolean = false,
    val passwordHasError: Boolean = false,
    val isLoading: Boolean = false
) : ReduxState