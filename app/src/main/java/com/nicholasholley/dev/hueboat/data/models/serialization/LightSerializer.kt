package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.*
import com.nicholasholley.dev.hueboat.data.models.HueLight
import com.nicholasholley.dev.hueboat.data.models.HueState
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueLightWrapper
import com.nicholasholley.dev.hueboat.util.log.Log
import java.lang.reflect.Type
import javax.inject.Inject

class LightSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueLightWrapper> {
    override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
    ): HueLightWrapper {
        return HueLightWrapper().apply {
            lights = HashMap()
            json?.asJsonObject?.entrySet()?.let {
                for (entryItem in it){
                    entryToHueLight(entryItem)?.let {
                        lights?.put(entryItem.key, it)
                    }
                }
            }
        }

    }

    private fun entryToHueLight(obj: Map.Entry<String, JsonElement>): HueLight? {
        val currentValue: JsonObject = obj.value.asJsonObject
        if (obj.key.toLongOrNull() != null) {
            return gson.fromJson(obj.value, HueLight::class.java).apply {
                id = obj.key.toLong()
            }
        } else {
            return null
        }
    }
}