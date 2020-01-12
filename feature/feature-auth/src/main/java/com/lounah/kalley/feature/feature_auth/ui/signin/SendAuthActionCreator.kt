package com.lounah.kalley.feature.feature_auth.ui.signin

import com.lounah.kalley.core.architecture.extensions.andJust
import io.reactivex.Completable
import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.onErrorReturnAction

internal class SendAuthActionCreator(
    private val sendAuth: (Credentials) -> Completable,
    private val onAuthSucceed: Action,
    private val showLoading: Action,
    private val onAuthFailed: Action
) : ActionCreator<Observable<Credentials>> {

    override fun invoke(event: Observable<Credentials>): Observable<Action> {
        return event.switchMap { credentials ->
            sendAuth(credentials)
                .andJust(onAuthSucceed)
                .startWith(showLoading)
                .onErrorReturnAction(onAuthFailed)
        }
    }
}