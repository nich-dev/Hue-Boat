package com.nicholasholley.dev.hueboatsdk.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueCondition(
        @PrimaryKey var id:         Long = 0L,
        @Expose var address:        String? = null,
        @Expose var operator:       String? = null,
        @Expose var value:          String? = null
): RealmObject()