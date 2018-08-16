package com.nicholasholley.dev.hueboatsdk.data.models.wrapper

import com.nicholasholley.dev.hueboatsdk.data.models.HueScene

class HueSceneWrapper(
        var scenes: MutableMap<String, HueScene>? = null
) {
    private fun scenesToString(): String {
        var str = ""
        var separator = "["
        for (l in scenes ?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueSceneWrapper(lights=${scenesToString()})"
    }
}