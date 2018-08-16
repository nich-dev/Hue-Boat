package com.nicholasholley.dev.hueboatsdk.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueUser(
        @PrimaryKey var id:         Long = 0,
        @Expose var username:       String? = null,
        @Expose var devicetype:     String? = null
): RealmObject()