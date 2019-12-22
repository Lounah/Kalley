@file:Suppress("LeakingThis")

package com.lounah.kalley.core.architecture.redux

import com.freeletics.rxredux.reduxStore
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

abstract class ReduxPresenter(initialState: ReduxState) {

    abstract val sideEffects: List<SideEff>
    abstract val reducer: ReduxReducer

    private val input: Relay<ReduxAction> = PublishRelay.create()

    val currentState: Observable<ReduxState> = input.reduxStore(initialState, sideEffects, reducer)

    fun accept(action: ReduxAction) = input.accept(action)
}