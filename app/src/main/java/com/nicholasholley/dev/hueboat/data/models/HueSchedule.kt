package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueSchedule(
        @PrimaryKey var id:         Long = 0L,
        @Expose var name:           String? = null,
        @Expose var description:    String? = null,
        @Expose var localtime:      String? = null,
        @Expose var status:         String? = null,
        @Expose var autodelete:     Boolean = true,
        @Expose var recycle:        Boolean = false,
        @Expose var command:        HueCommand? = null
): RealmObject()