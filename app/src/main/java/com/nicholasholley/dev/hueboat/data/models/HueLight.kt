package com.nicholasholley.dev.hueboat.data.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class HueLight(
        @PrimaryKey var id: Long = 0,
        var state: HueState? = null,
        var type: String? = null,
        var name: String? = null,
        var modelid: String? = null,
        var uniqueid: String? = null,
        var manufacturername: String? = null,
        var productname: String? = null,
        var swversion: String? = null,
        var luminaireuniqueid: String? = null
): RealmObject()