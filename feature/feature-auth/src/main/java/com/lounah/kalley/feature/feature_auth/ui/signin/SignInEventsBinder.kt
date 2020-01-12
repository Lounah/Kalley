package com.lounah.kalley.feature.feature_auth.ui.signin

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEventsBinder
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionOnEvent

internal class SignInEventsBinder(
    private val changeSignInButtonState: ActionCreator<Observable<Credentials>>,
    private val performSignIn: ActionCreator<Observable<Credentials>>,
    private val closeAuth: ActionOnEvent
) : BaseEventsBinder<SignInEvents>() {

    override fun bindInternal(events: SignInEvents): Observable<Action> {
        return Observable.merge(
            changeSignInButtonState(events.credentialsChanged),
            performSignIn(events.signInClicked),
            closeAuth(events.closeAuth)
        )
    }
}