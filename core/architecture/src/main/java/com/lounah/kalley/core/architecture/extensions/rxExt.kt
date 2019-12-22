package com.lounah.kalley.core.architecture.extensions

import android.util.Log
import com.lounah.kalley.core.architecture.redux.ReduxAction
import com.lounah.kalley.core.architecture.redux.ReduxEffect
import io.reactivex.*
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.disposables.Disposable

private val onNextStub: (Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Observable<T>.subscribeTo(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onNext: (T) -> Unit = onNextStub
): Disposable = subscribe(onNext, onError, onComplete)

@CheckReturnValue
@BackpressureSupport(BackpressureKind.UNBOUNDED_IN)
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Flowable<T>.subscribeTo(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onNext: (T) -> Unit = onNextStub
): Disposable = subscribe(onNext, onError, onComplete)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Single<T>.subscribeTo(
    onError: (Throwable) -> Unit = onErrorStub,
    onSuccess: (T) -> Unit = onNextStub
): Disposable = subscribe(onSuccess, onError)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any> Maybe<T>.subscribeTo(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub,
    onSuccess: (T) -> Unit = onNextStub
): Disposable = subscribe(onSuccess, onError, onComplete)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun Completable.subscribeTo(
    onError: (Throwable) -> Unit = onErrorStub,
    onComplete: () -> Unit = onCompleteStub
): Disposable = subscribe(onComplete, onError)

inline fun <reified T : ReduxEffect> Observable<T>.startWithAction(action: T): Observable<T>
        = filter { it is T }.startWith(Observable.just<T>(action))

fun Completable.mapToAction(action: ReduxAction): Observable<ReduxAction>
    = andThen(Observable.just(action))

fun <T : ReduxEffect> Observable<ReduxAction>.mapToEffect(effect: T): Observable<T>
        = map<T> { effect }

fun <T : ReduxEffect> Completable.mapToEffect(effect: T): Observable<T>
        = toObservable<T>().map<T> { effect }
//
//fun <T : ReduxAction> Observable<T>.onErrorReturnEffect(effect: T): Observable<T>
//        = onErrorResumeNext<T> { Observable.just<T>(effect) }

fun Completable.onErrorReturnAction(action: ReduxAction): Observable<out ReduxAction>
        = toObservable<ReduxAction>().onErrorReturnItem(action)