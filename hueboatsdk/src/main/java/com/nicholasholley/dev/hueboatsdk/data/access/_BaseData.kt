package com.nicholasholley.dev.hueboatsdk.data.access

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults

interface _BaseData<T: RealmModel> {
    val clazz: Class<T>
    var realm: Realm

    fun close() {
        if (!realm.isClosed) realm.close()
    }

    fun save(obj: T)
    fun save(objs: List<T>)

    fun delete(obj: T)
    fun delete(objs: List<T>)
    fun deleteWhere(field: String? = null, value: Any? = null)

    fun findFirst(field: String? = null, value: Any? = null): T?
    fun find(field: String? = null, value: Any? = null): List<T>
    fun findFirstManaged(field: String? = null, value: Any? = null): T?
    fun findManaged(field: String? = null, value: Any? = null): RealmResults<T>

    companion object {
        const val REALM_FIELD_TYPE_EXCEPTION_MSG = "Must be a type that realm can query against \n Date, Boolean, Byte, ByteArray, Double, Float, Int, Long, Short, String"
    }
}