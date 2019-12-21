package com.lounah.kalley.core.architecture.redux

import androidx.lifecycle.Lifecycle
import com.lounah.kalley.core.architecture.disposeOnDestroy
import io.reactivex.android.schedulers.AndroidSchedulers

inline fun <reified State : ReduxState> ReduxPresenter.bind(
    target: Lifecycle,
    crossinline onStateChanged: (State) -> Unit = {},
    crossinline onEffect: (ReduxEffect) -> Unit = {}
) = state
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { if (it is ReduxEffect) onEffect(it) else onStateChanged(it as State) }
    .disposeOnDestroy(target)