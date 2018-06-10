package com.nicholasholley.dev.hueboatsdk.data.network.json

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import io.realm.RealmObject

/**
 * Documentation
 */
class RealmExclusionStrategy: ExclusionStrategy {
    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
        return false
    }

    override fun shouldSkipField(f: FieldAttributes?): Boolean {
        return f!!.declaringClass!!.equals(RealmObject::class.java)
    }
}