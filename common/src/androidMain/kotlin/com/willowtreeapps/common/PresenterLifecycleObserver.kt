package com.willowtreeapps.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.willowtreeapps.common.external.*

class PresenterLifecycleObserver(val view: ViewWithProvider<*>): LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onAttach() {
        rootDispatch(AttachView(view))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onDetach() {
        rootDispatch(DetachView(view))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onClear() {
        rootDispatch(ClearView(view))
    }

}
