package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueRule
import com.nicholasholley.dev.hueboatsdk.data.models.HueRuleWrapper
import java.lang.reflect.Type

class RuleSerializer constructor(
        val gson: Gson
) : JsonDeserializer<HueRuleWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueRuleWrapper =
            HueRuleWrapper().apply {
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueRule? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueRule::class.java).apply { id = it }
            }
}