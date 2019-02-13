package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueSensor
import com.nicholasholley.dev.hueboatsdk.data.models.HueSensorWrapper
import java.lang.reflect.Type

class SensorSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueSensorWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueSensorWrapper =
            HueSensorWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSensor? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueSensor::class.java).apply { id = it }
            }
}