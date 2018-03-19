package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class HueSensor(
        @PrimaryKey var id:             Long = 0L,
        @Expose var name:               String? = null,
        @Expose var modelid:            String? = null,
        @Expose var swversion:          String? = null,
        @Expose var type:               String? = null,
        @Expose var uniqueid:           String? = null,
        @Expose var manufacturername:   String? = null,
        @Expose var recycle:            Boolean? = null,
        @Expose var config:             HueSensorConfig? = null
): RealmObject()
