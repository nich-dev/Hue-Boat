package com.nicholasholley.dev.hueboat.util.livedata

import android.arch.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmObject

open class LiveRealmDataSingle<T: RealmObject>(
    val result: T
): LiveData<T>() {

    private val listener: RealmChangeListener<T> =
        RealmChangeListener<T> {
            value = result
        }

    override fun onActive() {
        super.onInactive()
        result.addChangeListener(listener)
    }

    override fun onInactive() {
        super.onActive()
        result.removeChangeListener(listener)
    }
}