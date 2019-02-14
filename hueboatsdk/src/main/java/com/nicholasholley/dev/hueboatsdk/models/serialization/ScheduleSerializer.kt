package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.models.HueSchedule
import com.nicholasholley.dev.hueboatsdk.models.HueScheduleWrapper
import java.lang.reflect.Type

class ScheduleSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueScheduleWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueScheduleWrapper =
        HueScheduleWrapper().apply {
            json?.asJsonObject?.entrySet()?.forEach { item ->
                put(item.key, entryToHue(item))
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSchedule =
            gson.fromJson(obj.value, HueSchedule::class.java).apply { id = obj.key }
}