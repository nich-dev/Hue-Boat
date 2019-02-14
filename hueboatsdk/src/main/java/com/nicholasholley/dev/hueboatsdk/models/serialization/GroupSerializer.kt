package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.models.HueGroup
import com.nicholasholley.dev.hueboatsdk.models.HueGroupWrapper
import java.lang.reflect.Type

class GroupSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueGroupWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueGroupWrapper =
        HueGroupWrapper().apply {
            json?.asJsonObject?.entrySet()?.forEach { item ->
                put(item.key, entryToHue(item))
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueGroup =
            gson.fromJson(obj.value, HueGroup::class.java).apply { id = obj.key }
}