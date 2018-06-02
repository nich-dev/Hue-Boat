package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboat.data.models.HueSensor
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueSensorWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class SensorSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueSensorWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueSensorWrapper =
            HueSensorWrapper().apply {
                sensors = HashMap()
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        sensors?.put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSensor? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueSensor::class.java).apply { id = it }
            }
}