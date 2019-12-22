package com.lounah.kalley.feature.feature_auth.ui.signin.sideeff

import com.freeletics.rxredux.StateAccessor
import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxState
import com.lounah.kalley.core.architecture.redux.SideEff
import com.lounah.kalley.feature.feature_auth.ui.signin.AuthAction.*
import io.reactivex.Observable
import io.reactivex.rxkotlin.ofType

internal class SendAuthSideEff : SideEff {

    override fun invoke(
        actions: Observable<ReduxAction>,
        state: StateAccessor<ReduxState>
    ): Observable<out ReduxAction> = actions.ofType<OnSignInClicked>()
        .map {
            SendAuth()
        }
}