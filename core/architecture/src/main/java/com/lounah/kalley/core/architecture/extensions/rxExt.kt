package com.lounah.kalley.core.architecture.extensions

import io.reactivex.*
import io.reactivex.annotations.BackpressureKind
import io.reactivex.annotations.BackpressureSupport
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

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

fun <T> Completable.andJust(item: T): Observable<T> {
    return andThen(Observable.just(item))
}

fun <T> Completable.map(block: () -> T): Observable<T> {
    return andThen(Observable.fromCallable(block))
}

fun Completable.andError(throwable: Throwable): Completable {
    return andThen(Completable.error(throwable))
}

fun <T> Completable.andSingleError(throwable: Throwable): Single<T> {
    return andThen(Single.error(throwable))
}

fun <T> BehaviorSubject<T>.valueOrThrow(): T {
    return value ?: throw IllegalStateException("subject value is null")
}

fun <T> Subject<T>.set(value: T) {
    onNext(value)
}

fun emptyCompletable(): () -> Completable {
    return Completable::complete
}

fun <T> emptyTypedCompletable(): (T) -> Completable {
    return { Completable.complete() }
}

fun <T> T.justObservable(): Observable<T> {
    return Observable.just(this)
}

fun <T> T.justSingle(): Single<T> {
    return Single.just(this)
}