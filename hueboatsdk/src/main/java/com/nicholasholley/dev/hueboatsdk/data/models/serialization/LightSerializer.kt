package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboatsdk.data.models.HueLight
import com.nicholasholley.dev.hueboatsdk.data.models.HueLightWrapper
import java.lang.reflect.Type

class LightSerializer constructor(
        private val gson: Gson
) : JsonDeserializer<HueLightWrapper> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueLightWrapper =
        HueLightWrapper().apply {
            json?.asJsonObject?.entrySet()?.forEach { item ->
                entryToHue(item)?.let {
                    put(item.key, it)
                }
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueLight? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueLight::class.java).apply { id = it }
            }
}