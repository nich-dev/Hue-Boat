package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueGroup(
        @PrimaryKey var id: Long = 0L,
        @Expose     var name:       String? = null,
        @Expose     var type:       String? = null,
        @SerializedName("class")
        @Expose     var category:   String? = null,
        @Expose     var modelid:    String? = null,
        @Expose     var uniqueid:   String? = null,
        @Expose     var action:     HueAction? = null,
        @SerializedName("lights")
        @Expose     var lightIds:   RealmList<String> = RealmList(),
                    var lights:     RealmList<HueLight> = RealmList()
): RealmObject()