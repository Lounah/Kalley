package com.lounah.kalley.core.architecture.redux

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lounah.kalley.core.architecture.disposeOnDestroy
import com.lounah.kalley.core.architecture.extensions.subscribeTo
import io.reactivex.android.schedulers.AndroidSchedulers

inline fun <reified State : ReduxState, reified Effect : ReduxEffect> ReduxPresenter.bind(
    target: Lifecycle,
    crossinline onStateChanged: (State) -> Unit = {},
    crossinline onEffect: (Effect) -> Unit = {}
) = currentState()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeTo(onNext = { if (it is Effect) onEffect(it) else onStateChanged(it as State) })
    .disposeOnDestroy(target)

inline fun <reified State : ReduxState, reified Effect : ReduxEffect> ReduxPresenter.bind(
    target: Fragment,
    crossinline onStateChanged: (State) -> Unit = {},
    crossinline onEffect: (Effect) -> Unit = {}
) = currentState()
    .observeOn(AndroidSchedulers.mainThread())
    .subscribeTo(onNext = {
        if (it is Effect) onEffect(it)
        else onStateChanged(it as State)
    })
    .disposeOnDestroy(target.lifecycle)