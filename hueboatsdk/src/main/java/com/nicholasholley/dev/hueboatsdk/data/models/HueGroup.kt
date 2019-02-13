package com.nicholasholley.dev.hueboatsdk.data.models

import com.google.gson.annotations.SerializedName

data class HueGroup(
        var id:         Long = 0L,
        var name:       String? = null,
        var type:       String? = null,
        @SerializedName("class")
        var category:   String? = null,
        var modelid:    String? = null,
        var uniqueid:   String? = null,
        var action:     HueState? = null,
        @SerializedName("lights")
        var lightIds:   List<String> = listOf(),
        var lights:     List<HueLight> = listOf()
)
