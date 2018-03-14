package com.nicholasholley.dev.hueboat.data.models

import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

class HueState(
        @PrimaryKey var id: Long = 0L,
        var on: Boolean = false,
        var reachable: Boolean = false,
        var bri: Int = 1,
        var hue: Int = 0,
        var sat: Int = 0,
        var ct: Int = 0,
        var xy: List<Double>? = null,
        var alert: String? = null,
        var effect: String? = null,
        var colormode: String? = null,
        @LinkingObjects("state")
        val lights: RealmResults<HueLight>? = null
): RealmObject()