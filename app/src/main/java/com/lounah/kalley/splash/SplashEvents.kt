package com.lounah.kalley.splash

import ru.tinkoff.eba.BaseEvents
import ru.tinkoff.eba.actioncreators.EventObservable

internal interface SplashEvents : BaseEvents {
    val bindEvent: EventObservable
}

internal class SplashEventsImpl(
    override val unbindEvent: EventObservable,
    override val bindEvent: EventObservable
) : SplashEvents