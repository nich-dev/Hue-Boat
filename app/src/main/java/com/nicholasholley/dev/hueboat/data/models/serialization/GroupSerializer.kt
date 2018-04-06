package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboat.data.models.HueGroup
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueGroupWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class GroupSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueGroupWrapper> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): HueGroupWrapper {
        return HueGroupWrapper().apply {
            groups = HashMap()
            json?.asJsonObject?.entrySet()?.let {
                for (entryItem in it){
                    entryToHueLight(entryItem)?.let {
                        groups?.put(entryItem.key, it)
                    }
                }
            }
        }

    }

    private fun entryToHueLight(obj: Map.Entry<String, JsonElement>): HueGroup? {
        val currentValue: JsonObject = obj.value.asJsonObject
        if (obj.key.toLongOrNull() != null) {
            return gson.fromJson(obj.value, HueGroup::class.java).apply {
                id = obj.key.toLong()
            }
        } else {
            return null
        }
    }
}