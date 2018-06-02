package com.nicholasholley.dev.hueboat.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboat.data.models.HueRule
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueRuleWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class RuleSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueRuleWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueRuleWrapper =
            HueRuleWrapper().apply {
                rules = HashMap()
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        rules?.put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueRule? =
            obj.key.toLongOrNull()?.let {
                gson.fromJson(obj.value, HueRule::class.java).apply { id = it }
            }
}