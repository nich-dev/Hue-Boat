package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboat.data.models.HueSchedule
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueScheduleWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class ScheduleSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueScheduleWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueScheduleWrapper =
        HueScheduleWrapper().apply {
            schedules = HashMap()
            json?.asJsonObject?.entrySet()?.forEach { item ->
                entryToHue(item)?.let {
                    schedules?.put(item.key, it)
                }
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSchedule? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueSchedule::class.java).apply { id = it }
            }
}