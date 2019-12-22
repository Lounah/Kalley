@file:Suppress("CanSealedSubClassBeObject")

package com.lounah.kalley.feature.feature_auth.ui.signin

import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxEffect
import com.lounah.kalley.core.architecture.redux.ReduxState

internal sealed class AuthAction : ReduxAction {
    class OnSignInClicked : AuthAction()
    class SendAuthStarted : AuthAction()
    data class OnUsernameChanged(val username: String) : AuthAction()
    data class OnPasswordChanged(val password: String) : AuthAction()
}

internal sealed class AuthEffect : ReduxEffect {
    class ShowAuthError : AuthEffect()
    class OnAuthSuccess : AuthEffect()
    data class AuthButtonStateChanged(
        val usernameIsValid: Boolean,
        val passwordIsValid: Boolean,
        val enabled: Boolean = usernameIsValid && passwordIsValid
    ) : AuthEffect()
}

internal data class AuthState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false
) : ReduxState