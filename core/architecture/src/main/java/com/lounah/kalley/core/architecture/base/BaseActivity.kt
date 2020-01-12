package com.lounah.kalley.core.architecture.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

abstract class BaseActivity(@LayoutRes private val layout: Int? = null) : AppCompatActivity() {

    val bindEvent = Observable.just(true)
    val unbindEvent = PublishSubject.create<Unit>()
    val resumeEvent = PublishSubject.create<Unit>()
    val pauseEvent = PublishSubject.create<Unit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layout?.let(::setContentView)
    }

    override fun onResume() {
        super.onResume()
        resumeEvent.onNext(Unit)
    }

    override fun onPause() {
        super.onPause()
        pauseEvent.onNext(Unit)
    }

    override fun onDestroy() {
        unbindEvent.onNext(Unit)
        super.onDestroy()
    }
}