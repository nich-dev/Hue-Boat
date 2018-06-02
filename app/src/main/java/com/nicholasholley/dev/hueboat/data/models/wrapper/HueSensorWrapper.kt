package com.nicholasholley.dev.hueboat.data.models.wrapper

import com.nicholasholley.dev.hueboat.data.models.HueSensor

class HueSensorWrapper(
        var sensors: MutableMap<String, HueSensor>? = null
) {
    private fun sensorsToString(): String {
        var str = ""
        var separator = "["
        for (l in sensors?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueSensorWrapper(lights=${sensorsToString()})"
    }
}