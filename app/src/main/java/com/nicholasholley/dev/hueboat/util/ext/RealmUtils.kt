@file:JvmName("RealmUtils")
package com.nicholasholley.dev.hueboat.util.ext

import com.nicholasholley.dev.hueboat.util.livedata.LiveRealmData
import com.nicholasholley.dev.hueboat.util.livedata.LiveRealmDataSingle
import com.nicholasholley.dev.hueboat.util.livedata.SingleEmitLiveRealmDataSingle
import com.nicholasholley.dev.hueboat.util.livedata.SingleEmitLiveRealmData
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

fun <T: RealmModel> RealmResults<T>.asLiveData() = LiveRealmData<T>(this)
fun <T: RealmObject> T.asLiveData() = LiveRealmDataSingle<T>(this)
fun <T: RealmModel> RealmResults<T>.asSingleEmitLiveData() = SingleEmitLiveRealmData<T>(this)
fun <T: RealmObject> T.asSingleEmitLiveData() = SingleEmitLiveRealmDataSingle<T>(this)