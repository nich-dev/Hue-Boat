package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueLight
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueLightWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class LightSerializer @Inject constructor(
        private val gson: Gson
) : JsonDeserializer<HueLightWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueLightWrapper =
        HueLightWrapper().apply {
            lights = HashMap()
            json?.asJsonObject?.entrySet()?.forEach { item ->
                entryToHue(item)?.let {
                    lights?.put(item.key, it)
                }
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueLight? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueLight::class.java).apply { id = it }
            }
}