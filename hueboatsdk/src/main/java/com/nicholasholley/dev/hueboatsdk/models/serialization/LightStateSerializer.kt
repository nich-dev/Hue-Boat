package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboatsdk.models.HueLightStateWrapper
import com.nicholasholley.dev.hueboatsdk.models.HueState
import java.lang.reflect.Type

class LightStateSerializer constructor(
        private val gson: Gson
) : JsonDeserializer<HueLightStateWrapper>, JsonSerializer<HueLightStateWrapper> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueLightStateWrapper =
            HueLightStateWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueState =
            gson.fromJson(obj.value, HueState::class.java).apply { id = obj.key }

    override fun serialize(src: HueLightStateWrapper?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val serializedObject = JsonObject()
        src?.forEach { entry ->
            serializedObject.add(entry.key, gson.fromJson(gson.toJson(entry.value), JsonElement::class.java))
        }
        return serializedObject
    }
}
