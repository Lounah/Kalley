package com.lounah.kalley.core.architecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.subjects.PublishSubject

abstract class BaseFragment(@LayoutRes private val layout: Int) : Fragment() {

    val parentActivity get() = activity ?: error("Parent activity is null.")

    val bindEvent = PublishSubject.create<Unit>()
    val unbindEvent = PublishSubject.create<Unit>()
    val resumeEvent = PublishSubject.create<Unit>()
    val pauseEvent = PublishSubject.create<Unit>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindEvent.onNext(Unit)
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