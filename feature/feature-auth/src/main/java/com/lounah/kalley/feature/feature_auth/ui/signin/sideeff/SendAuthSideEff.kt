package com.lounah.kalley.feature.feature_auth.ui.signin.sideeff

import com.freeletics.rxredux.StateAccessor
import com.lounah.kalley.core.architecture.extensions.mapToEffect
import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxEffect
import com.lounah.kalley.core.architecture.redux.ReduxState
import com.lounah.kalley.core.architecture.redux.SideEff
import com.lounah.kalley.feature.feature_auth.domain.AuthSharedPrefs
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthEffect.*
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthState
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType
import java.util.concurrent.TimeUnit

internal class SendAuthSideEff(private val prefs: AuthSharedPrefs) : SideEff {

    override fun invoke(
        actions: Observable<ReduxAction>,
        state: StateAccessor<ReduxState>
    ): Observable<out ReduxAction> = actions.ofType<OnSignInClicked>()
        .map { state() as AuthState }
        .flatMapCompletable {
            prefs.saveUsername(it.username)
                .andThen(prefs.savePassword(it.password))
        }
        .mapToEffect<ReduxEffect>(OnAuthSuccess())
//        .onErrorReturnEffect(ShowAuthError())
}