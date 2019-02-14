package com.nicholasholley.dev.hueboatsdk.models

data class HueLight(
        var id:                 String? = null,
        var state:              HueState? = null,
        var type:               String? = null,
        var name:               String? = null,
        var modelid:            String? = null,
        var uniqueid:           String? = null,
        var manufacturername:   String? = null,
        var productname:        String? = null,
        var swversion:          String? = null,
        var luminaireuniqueid:  String? = null
)
