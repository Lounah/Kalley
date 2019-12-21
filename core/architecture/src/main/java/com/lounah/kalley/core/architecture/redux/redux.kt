package com.lounah.kalley.core.architecture.redux

import com.freeletics.rxredux.Reducer
import com.freeletics.rxredux.SideEffect

typealias ReduxReducer = Reducer<ReduxState, ReduxAction>
typealias SideEff = SideEffect<ReduxState, ReduxAction>

interface ReduxAction
interface ReduxState
interface ReduxEffect : ReduxState,
    ReduxAction

@Suppress("FunctionName")
inline fun <reified State : ReduxState> BaseReducer(
    noinline extender: (State, ReduxAction) -> State
): ReduxReducer = { state, action ->
    if (action is ReduxEffect) state else extender(state as State, action)
}