package com.nicholasholley.dev.hueboatsdk.models

data class HueState(
        var id:             String? = null,
        var on:             Boolean = false,
        var reachable:      Boolean = false,
        var bri:            Int = 1, // Range [1, 254]
        var hue:            Int = 0, // Range [0, 65535]
        var sat:            Int = 0, // Range [0, 254]
        var ct:             Int = 0, // Range [153 (6500K), 500 (2000K)]
        var xy:             List<Double>? = null, // CIE color space
        var alert:          String? = null,
        var effect:         String? = null,
        var colormode:      String? = null,
        var scene:          String? = null
)
