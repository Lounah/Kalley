package com.lounah.kalley.feature.feature_auth.ui.signin

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.Observables
import ru.tinkoff.eba.BaseEvents
import ru.tinkoff.eba.actioncreators.EventObservable

typealias Credentials = Pair<String, String>

internal interface SignInEvents : BaseEvents {
    val usernameChanged: Observable<String>
    val passwordChanged: Observable<String>
    val signInClicked: Observable<Credentials>
    val credentialsChanged: Observable<Credentials>
}

internal class SignInEventsImpl(override val unbindEvent: EventObservable,
                                usernameChanged: Observable<CharSequence>,
                                passwordChanged: Observable<CharSequence>,
                                signInClicked: EventObservable) : SignInEvents {

    override val usernameChanged: Observable<String> =
        usernameChanged.map(CharSequence::toString).share()

    override val passwordChanged: Observable<String> =
        passwordChanged.map(CharSequence::toString).share()

    override val credentialsChanged: Observable<Credentials> =
        Observables.combineLatest(this.usernameChanged, this.passwordChanged) { username, password ->
            username to password
        }.share()

    override val signInClicked: Observable<Credentials> =
        signInClicked.withLatestFrom(credentialsChanged, BiFunction { _, credentials -> credentials })
}