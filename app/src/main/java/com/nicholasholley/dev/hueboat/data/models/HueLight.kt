package com.nicholasholley.dev.hueboat.data.models

import com.google.gson.annotations.Expose
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HueLight(
        @PrimaryKey var id:             Long = 0,
        @Expose var state:              HueState? = null,
        @Expose var type:               String? = null,
        @Expose var name:               String? = null,
        @Expose var modelid:            String? = null,
        @Expose var uniqueid:           String? = null,
        @Expose var manufacturername:   String? = null,
        @Expose var productname:        String? = null,
        @Expose var swversion:          String? = null,
        @Expose var luminaireuniqueid:  String? = null
): RealmObject() {
    override fun toString(): String {
        return "HueLight(id=$id, state=$state, type=$type, name=$name, modelid=$modelid, uniqueid=$uniqueid, manufacturername=$manufacturername, productname=$productname, swversion=$swversion, luminaireuniqueid=$luminaireuniqueid)"
    }
}