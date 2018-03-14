package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

class HueAction(
        @PrimaryKey var id:         Long = 0L,
        @Expose var on:             Boolean = false,
        @Expose var reachable:      Boolean = false,
        @Expose var bri:            Int = 1,
        @Expose var hue:            Int = 0,
        @Expose var sat:            Int = 0,
        @Expose var ct:             Int = 0,
        @Expose var xy:             RealmList<Double>? = RealmList(),
        @Expose var alert:          String? = null,
        @Expose var effect:         String? = null,
        @Expose var colormode:      String? = null,
        @SerializedName("scene")
        @Expose var scene:          String? = null,
        @LinkingObjects("action")
                val groups:         RealmResults<HueGroup>? = null
): RealmObject()