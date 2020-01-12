package com.lounah.kalley.splash

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEventsBinder
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionOnEvent

internal class SplashEventsBinder(
    private val checkUserCredentials: ActionOnEvent
) : BaseEventsBinder<SplashEvents>() {

    override fun bindInternal(events: SplashEvents): Observable<Action> {
        return checkUserCredentials(events.bindEvent)
    }
}