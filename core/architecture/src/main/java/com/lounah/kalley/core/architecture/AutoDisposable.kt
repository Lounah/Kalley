@file:Suppress("unused")

package com.lounah.kalley.core.architecture

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class AutoDisposable : LifecycleObserver {

    private lateinit var compositeDisposable: CompositeDisposable

    fun bindTo(lifecycle: Lifecycle) {
        lifecycle.addObserver(this)
        compositeDisposable = CompositeDisposable()
    }

    fun add(disposable: Disposable) {
        if (::compositeDisposable.isInitialized) {
            compositeDisposable.add(disposable)
        } else error("CompositeDisposable is not initialized.")
    }

    @OnLifecycleEvent(ON_DESTROY)
    fun onDestroy() {
        compositeDisposable.dispose()
    }
}

fun Disposable.disposeOnDestroy(lifecycle: Lifecycle) {
    AutoDisposable().apply {
        bindTo(lifecycle)
        add(this@disposeOnDestroy)
    }
}