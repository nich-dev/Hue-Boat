package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.models.HueSensor
import com.nicholasholley.dev.hueboatsdk.models.HueSensorWrapper
import java.lang.reflect.Type

class SensorSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueSensorWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueSensorWrapper =
            HueSensorWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    put(item.key, entryToHue(item))
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueSensor =
            gson.fromJson(obj.value, HueSensor::class.java).apply { id = obj.key }
}