package com.nicholasholley.dev.hueboatsdk.models

data class HueSensor(
        var id:                 String? = null,
        var name:               String? = null,
        var modelid:            String? = null,
        var swversion:          String? = null,
        var type:               String? = null,
        var uniqueid:           String? = null,
        var manufacturername:   String? = null,
        var recycle:            Boolean? = null,
        var config:             HueSensorConfig? = null
)

data class HueSensorConfig(
        var on:         Boolean? = null,
        var reachable:  Boolean? = null,
        var battery:    Int? = null
)
