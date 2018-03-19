package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class HueRule(
        @PrimaryKey var id:         Long = 0L,
        @Expose var name:           String? = null,
        @Expose var lasttriggered:  String? = null,
        @Expose var creationtime:   String? = null,
        @Expose var owner:          String? = null,
        @Expose var status:         String? = null,
        @Expose var conditions:     RealmList<HueCondition>,
        @Expose var actions:        RealmList<HueRuleAction>
): RealmObject()