package com.lounah.kalley.splash.di

import com.lounah.kalley.core.architecture.extensions.andJust
import com.lounah.kalley.core.di.Di
import com.lounah.kalley.core.storage.AuthSharedPrefs
import com.lounah.kalley.splash.SplashEventsBinder
import com.lounah.kalley.splash.domain.CheckCredentials
import io.reactivex.Completable
import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.*

internal class SplashComponent(
    private val navigateToMain: Action,
    private val navigateToAuth: Action
) {

    fun binder(): SplashEventsBinder {
        val authPrefs = Di.get<AuthSharedPrefs>()
        val checkCredentials = CheckCredentials(authPrefs)
        val checkUserCredentialsActionCreator =
            CheckUserCredentialsActionCreator(checkCredentials, navigateToMain, navigateToAuth)
        return SplashEventsBinder(checkUserCredentialsActionCreator)
    }
}

internal class CheckUserCredentialsActionCreator(
    private val checkCredentials: () -> Completable,
    private val navigateToMain: Action,
    private val navigateToAuth: Action
) : ActionCreator<EventObservable> {

    override fun invoke(event: EventObservable): Observable<Action> {
        return event.switchMap {
            checkCredentials().andJust(navigateToMain)
                .onErrorReturnAction(navigateToAuth)
        }
    }
}