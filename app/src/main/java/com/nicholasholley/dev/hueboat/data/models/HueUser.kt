package com.nicholasholley.dev.hueboat.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class HueUser(
        @PrimaryKey var id: Long = 0,
        var username: String? = null,
        var devicetype: String? = null
): RealmObject()