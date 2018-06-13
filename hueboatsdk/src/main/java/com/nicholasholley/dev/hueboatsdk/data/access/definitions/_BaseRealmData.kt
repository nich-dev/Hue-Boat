package com.nicholasholley.dev.hueboatsdk.data.access.definitions

import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.data.access._BaseData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults
import java.util.*

abstract class _BaseRealmData<T>: _BaseData<T>
    where T : RealmModel {

    override var realm: Realm = Boat.getRealmInstance()

    fun makeSimpleQuery(field: String?, value: Any?): RealmQuery<T> =
            realm.where(clazz).apply {
                field?.let {
                    when (value){
                        // There's gotta be a better way, this is so wordy even with smart casting
                        is Date? ->         { equalTo(field, value) }
                        is Boolean? ->      { equalTo(field, value) }
                        is Byte? ->         { equalTo(field, value) }
                        is ByteArray? ->    { equalTo(field, value) }
                        is Double? ->       { equalTo(field, value) }
                        is Float? ->        { equalTo(field, value) }
                        is Int? ->          { equalTo(field, value) }
                        is Long? ->         { equalTo(field, value) }
                        is Short? ->        { equalTo(field, value) }
                        is String? ->       { equalTo(field, value) }
                        else -> throw IllegalArgumentException(_BaseData.REALM_FIELD_TYPE_EXCEPTION_MSG)
                    }
                }
            }

    override fun findFirst(field: String?, value: Any?): T? =
            findFirstManaged(field, value)?.let { realm.copyFromRealm(it) }

    override fun find(field: String?, value: Any?): List<T> =
            findManaged(field, value).let { realm.copyFromRealm(it) }

    override fun findFirstManaged(field: String?, value: Any?): T? =
            makeSimpleQuery(field, value).findFirst()

    override fun findManaged(field: String?, value: Any?): RealmResults<T> =
            makeSimpleQuery(field, value).findAll()
}