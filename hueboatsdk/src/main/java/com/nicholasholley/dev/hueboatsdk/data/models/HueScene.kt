package com.nicholasholley.dev.hueboatsdk.data.models

data class HueScene(
        var id:             String,
        var name:           String? = null,
        var type:           String? = null,
        var owner:          String? = null,
        var picture:        String? = null,
        var lastupdated:    String? = null,
        var recycle:        Boolean? = false,
        var locked:         Boolean? = null,
        var version:        Int? = null,
        var lightIds:       List<Long> = listOf(),
        var lightstates:    HueLightStateWrapper? = null
)
