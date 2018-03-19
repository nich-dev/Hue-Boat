package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

class HueRuleAction(
        @PrimaryKey var id:         Long = 0L,
        @Expose var method:         String? = null,
        @Expose var address:        String? = null,
        @Expose var body:           HueAction? = null,
        @LinkingObjects("action")
                val rules:          RealmResults<HueRule>? = null
): RealmObject()