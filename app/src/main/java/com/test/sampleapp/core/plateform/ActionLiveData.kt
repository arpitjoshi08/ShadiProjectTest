package com.test.sampleapp.core.plateform

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class ActionLiveData<T> : MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }

        super.observe(owner, Observer { data ->
            // We ignore any null values and early return
            if (data != null) {
                observer.onChanged(data)
                // We set the value to null straight after emitting the change to the observer
                value = null
            }
        })
    }

    // Just a nicely named method that wraps setting the value
    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}
