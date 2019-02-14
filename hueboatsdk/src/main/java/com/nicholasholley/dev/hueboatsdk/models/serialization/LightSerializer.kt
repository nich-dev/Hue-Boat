package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboatsdk.models.HueLight
import com.nicholasholley.dev.hueboatsdk.models.HueLightWrapper
import java.lang.reflect.Type

class LightSerializer constructor(
        private val gson: Gson
) : JsonDeserializer<HueLightWrapper> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueLightWrapper =
        HueLightWrapper().apply {
            json?.asJsonObject?.entrySet()?.forEach { item ->
                put(item.key, entryToHue(item))
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueLight =
            gson.fromJson(obj.value, HueLight::class.java).apply { id = obj.key }
}