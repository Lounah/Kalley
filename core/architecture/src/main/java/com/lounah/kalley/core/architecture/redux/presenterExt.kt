package com.lounah.kalley.core.architecture.redux

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lounah.kalley.core.architecture.disposeOnDestroy
import io.reactivex.android.schedulers.AndroidSchedulers

inline fun <reified State : ReduxState> ReduxPresenter.bind(
    target: Lifecycle,
    crossinline onStateChanged: (State) -> Unit = {},
    crossinline onEffect: (ReduxEffect) -> Unit = {}
) = currentState.distinctUntilChanged()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { if (it is ReduxEffect) onEffect(it) else onStateChanged(it as State) }
    .disposeOnDestroy(target)

inline fun <reified State : ReduxState, reified Effect: ReduxEffect> ReduxPresenter.bind(
    target: Fragment,
    crossinline onStateChanged: (State) -> Unit = {},
    crossinline onEffect: (Effect) -> Unit = {}
) = currentState.distinctUntilChanged()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe { if (it is Effect) onEffect(it) else onStateChanged(it as State) }
    .disposeOnDestroy(target.lifecycle)