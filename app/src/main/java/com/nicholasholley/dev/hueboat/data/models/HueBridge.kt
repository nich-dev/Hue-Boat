package com.nicholasholley.dev.hueboat.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueBridge(
        @PrimaryKey var ipAddress: String = "10.0.0.0",
        var macAddress: String? = null,
        var active: Boolean = true,
        var config: HueConfig? = null
): RealmObject()