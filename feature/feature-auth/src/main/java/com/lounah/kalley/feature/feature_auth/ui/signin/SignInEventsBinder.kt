package com.lounah.kalley.feature.feature_auth.ui.signin

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEventsBinder
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator

internal class SignInEventsBinder(
    private val changeSignInButtonState: ActionCreator<Observable<Credentials>>,
    private val performSignIn: ActionCreator<Observable<Credentials>>
) : BaseEventsBinder<SignInEvents>() {

    override fun bindInternal(events: SignInEvents): Observable<Action> {
        return Observable.merge(
            changeSignInButtonState(events.credentialsChanged),
            performSignIn(events.signInClicked)
        )
    }
}