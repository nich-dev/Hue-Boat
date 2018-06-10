package com.nicholasholley.dev.hueboatsdk.data.models.wrapper

import com.nicholasholley.dev.hueboatsdk.data.models.HueRule

class HueRuleWrapper(
        var rules: MutableMap<String, HueRule>? = null
) {
    private fun rulesToString(): String {
        var str = ""
        var separator = "["
        for (l in rules ?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueRuleWrapper(lights=${rulesToString()})"
    }
}