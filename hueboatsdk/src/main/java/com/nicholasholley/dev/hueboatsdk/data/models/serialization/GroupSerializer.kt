package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueGroup
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueGroupWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class GroupSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueGroupWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueGroupWrapper =
        HueGroupWrapper().apply {
            groups = HashMap()
            json?.asJsonObject?.entrySet()?.forEach { item ->
                entryToHue(item)?.let {
                    groups?.put(item.key, it)
                }
            }
        }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueGroup? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueGroup::class.java).apply { id = it }
            }
}