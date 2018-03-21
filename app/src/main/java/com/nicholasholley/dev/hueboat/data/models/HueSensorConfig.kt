package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

open class HueSensorConfig(
        @PrimaryKey var id:     Long = 0L,
        @Expose var on:         Boolean? = null,
        @Expose var reachable:  Boolean? = null,
        @Expose var battery:    Int? = null,
        @LinkingObjects("config")
                val sensors:    RealmResults<HueSensor>? = null
): RealmObject()