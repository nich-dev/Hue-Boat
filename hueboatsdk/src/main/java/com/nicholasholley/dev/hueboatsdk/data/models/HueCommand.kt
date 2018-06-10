package com.nicholasholley.dev.hueboatsdk.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueCommand(
        @PrimaryKey var id:         Long = 0L,
        @Expose var address:        String? = null,
        @Expose var method:         String? = null,
        @Expose var body:           String? = null
): RealmObject()