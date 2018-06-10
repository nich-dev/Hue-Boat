package com.nicholasholley.dev.hueboatsdk.data.models.wrapper

import com.nicholasholley.dev.hueboatsdk.data.models.HueLight

class HueLightWrapper(
        var lights: MutableMap<String, HueLight>? = null
) {
    private fun lightsToString(): String {
        var str = ""
        var separator = "["
        for (l in lights ?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueLightWrapper(lights=${lightsToString()})"
    }
}