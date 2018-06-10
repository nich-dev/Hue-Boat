package com.nicholasholley.dev.hueboatsdk.data.models.wrapper

import com.nicholasholley.dev.hueboatsdk.data.models.HueSchedule

class HueScheduleWrapper(
        var schedules: MutableMap<String, HueSchedule>? = null
) {
    private fun schedulesToString(): String {
        var str = ""
        var separator = "["
        for (l in schedules ?: hashMapOf()){
            str = str.plus(separator).plus(l.value.toString()).plus("]")
            separator = ",["
        }
        return str
    }

    override fun toString(): String {
        return "HueScheduleWrapper(objs=${schedulesToString()})"
    }
}