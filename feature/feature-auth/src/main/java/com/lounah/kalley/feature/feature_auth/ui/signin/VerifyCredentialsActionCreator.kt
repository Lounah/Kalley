package com.lounah.kalley.feature.feature_auth.ui.signin

import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionMapper

internal class VerifyCredentialsActionCreator(
    private val changeSignInState: ActionMapper<Boolean>,
    private val usernameValidator: (String) -> Boolean,
    private val passwordValidator: (String) -> Boolean
) : ActionCreator<Observable<Credentials>> {

    override fun invoke(event: Observable<Credentials>): Observable<Action> {
        return event.map { (username, password) ->
            changeSignInState(usernameValidator(username) && passwordValidator(password))
        }
    }
}