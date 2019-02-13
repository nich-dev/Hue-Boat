package com.nicholasholley.dev.hueboatsdk.data.models

data class HueSensor(
        var id:                 Long = 0L,
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
        var id:         Long = 0L,
        var on:         Boolean? = null,
        var reachable:  Boolean? = null,
        var battery:    Int? = null
)
