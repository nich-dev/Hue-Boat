package com.nicholasholley.dev.hueboatsdk.data.models

data class HueState(
        var id:             Long = 0L,
        var on:             Boolean = false,
        var reachable:      Boolean = false,
        var bri:            Int = 1,
        var hue:            Int = 0,
        var sat:            Int = 0,
        var ct:             Int = 0,
        var xy:             List<Double>? = null,
        var alert:          String? = null,
        var effect:         String? = null,
        var colormode:      String? = null,
        var scene:          String? = null
)
