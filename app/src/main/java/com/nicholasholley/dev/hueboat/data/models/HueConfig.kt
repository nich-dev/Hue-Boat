package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey

class HueConfig(
        @PrimaryKey var id:             Long = 0L,
        @Expose var name:               String? = null,
        @Expose var apiversion:         String? = null,
        @Expose var swversion:          String? = null,
        @Expose var proxyaddress:       String? = null,
        @Expose var ipaddress:          String? = null,
        @Expose var mac:                String? = null,
        @Expose var netmask:            String? = null,
        @Expose var gateway:            String? = null,
        @Expose var UTC:                String? = null,
        @Expose var localtime:          String? = null,
        @Expose var timezone:           String? = null,
        @Expose var modelid:            String? = null,
        @Expose var bridgeid:           String? = null,
        @Expose var replacesbridgeid:   String? = null,
        @Expose var datastoreversion:   String? = null,
        @Expose var starterkitid:       String? = null,
        @Expose var proxyport:          Int? = null,
        @Expose var zigbeechannel:      Int? = null,
        @Expose var factorynew:         Boolean? = null,
        @LinkingObjects("config")
                val bridges:            RealmResults<HueBridge>? = null
): RealmObject()