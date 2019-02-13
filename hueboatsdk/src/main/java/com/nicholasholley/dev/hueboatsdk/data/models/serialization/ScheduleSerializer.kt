package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueSchedule
import com.nicholasholley.dev.hueboatsdk.data.models.HueScheduleWrapper
import java.lang.reflect.Type

class ScheduleSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueScheduleWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueScheduleWrapper =
        HueScheduleWrapper().apply {
            json?.asJsonObject?.entrySet()?.forEach { item ->
                entryToHue(item)?.let {
                    put(item.key, it)
                }
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSchedule? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueSchedule::class.java).apply { id = it }
            }
}