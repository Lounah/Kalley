package com.lounah.kalley.feature.feature_auth.ui.signin.sideeff

import com.freeletics.rxredux.StateAccessor
import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxState
import com.lounah.kalley.core.architecture.redux.SideEff
import com.lounah.kalley.feature.feature_auth.domain.PasswordValidator
import com.lounah.kalley.feature.feature_auth.domain.UsernameValidator
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthEffect.*
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import io.reactivex.rxkotlin.zipWith

internal class ValidateCredentialsSideEff(
    private val usernameValidator: (String) -> Boolean = UsernameValidator(),
    private val passwordValidator: (String) -> Boolean = PasswordValidator()
) : SideEff {

    private val validateUsername: (Observable<ReduxAction>) -> Observable<Boolean> = { actions ->
        actions.ofType<OnUsernameChanged>()
            .map { usernameValidator(it.username) }
    }

    private val validatePassword: (Observable<ReduxAction>) -> Observable<Boolean> = { actions ->
        actions.ofType<OnPasswordChanged>()
            .map { passwordValidator(it.password) }
    }

    override fun invoke(
        actions: Observable<ReduxAction>,
        state: StateAccessor<ReduxState>
    ): Observable<out ReduxAction> {
        return validateUsername(actions).zipWith(validatePassword(actions)) { usernameIsValid, passwordIsValid ->
            ProceedAuthButtonStateChanged(usernameIsValid && passwordIsValid)
        }
    }
}