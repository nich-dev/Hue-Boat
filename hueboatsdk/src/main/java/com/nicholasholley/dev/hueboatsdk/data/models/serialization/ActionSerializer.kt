package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboatsdk.data.models.HueActionWrapper
import java.lang.reflect.Type

class ActionSerializer constructor(
        private val gson: Gson
) : JsonDeserializer<HueActionWrapper>, JsonSerializer<HueActionWrapper> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueActionWrapper =
            HueActionWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    put(item.key, item.value)
                }
            }

    override fun serialize(src: HueActionWrapper?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val serializedObject = JsonObject()
        src?.forEach { entry ->
            serializedObject.add(entry.key, gson.fromJson(gson.toJson(entry.value), JsonElement::class.java))
        }
        return serializedObject
    }
}
