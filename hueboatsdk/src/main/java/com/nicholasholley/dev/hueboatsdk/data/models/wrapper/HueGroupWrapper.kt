package com.nicholasholley.dev.hueboatsdk.data.models.wrapper

import com.nicholasholley.dev.hueboatsdk.data.models.HueGroup

class HueGroupWrapper(
        var groups: MutableMap<String, HueGroup>? = null
) {
    private fun groupsToString(): String {
        var str = ""
        var separator = "["
        for (l in groups ?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueGroupWrapper(groups=${groupsToString()})"
    }
}