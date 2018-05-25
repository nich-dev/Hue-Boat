package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboat.data.models.HueSchedule
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueScheduleWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class ScheduleSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueScheduleWrapper> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): HueScheduleWrapper {
        return HueScheduleWrapper().apply {
            schedules = HashMap()
            json?.asJsonObject?.entrySet()?.let {
                for (entryItem in it){
                    entryToHueLight(entryItem)?.let {
                        schedules?.put(entryItem.key, it)
                    }
                }
            }
        }
    }

    private fun entryToHueLight(obj: Map.Entry<String, JsonElement>): HueSchedule? {
        return if (obj.key.toLongOrNull() != null) {
            gson.fromJson(obj.value, HueSchedule::class.java).apply {
                id = obj.key.toLong()
            }
        } else null
    }
}