package com.nicholasholley.dev.hueboat.util.livedata

import android.arch.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

open class LiveRealmData<T: RealmModel>(
    val realmResults: RealmResults<T>
): LiveData<RealmResults<T>>() {

    private val listener: RealmChangeListener<RealmResults<T>> =
        RealmChangeListener<RealmResults<T>> {
            value = realmResults
        }

    override fun onActive() {
        super.onInactive()
        realmResults.addChangeListener(listener)
    }

    override fun onInactive() {
        super.onActive()
        realmResults.removeChangeListener(listener)
    }
}