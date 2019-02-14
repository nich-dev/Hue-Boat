package com.nicholasholley.dev.hueboatsdk.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboatsdk.models.HueCommandWrapper
import java.lang.reflect.Type

class CommandSerializer constructor(
        private val gson: Gson
) : JsonDeserializer<HueCommandWrapper>, JsonSerializer<HueCommandWrapper> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueCommandWrapper =
            HueCommandWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    put(item.key, item.value)
                }
            }

    override fun serialize(src: HueCommandWrapper?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val serializedObject = JsonObject()
        src?.forEach { entry ->
            serializedObject.add(entry.key, gson.fromJson(gson.toJson(entry.value), JsonElement::class.java))
        }
        return serializedObject
    }
}
