package com.nicholasholley.dev.hueboatsdk.data.models.serialization

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.nicholasholley.dev.hueboatsdk.data.models.HueScene
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueSceneWrapper
import java.lang.reflect.Type
import javax.inject.Inject

class SceneSerializer @Inject constructor(
        val gson: Gson
) : JsonDeserializer<HueSceneWrapper> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HueSceneWrapper =
            HueSceneWrapper().apply {
                scenes = HashMap()
                json?.asJsonObject?.entrySet()?.forEach { item ->
                    entryToHue(item)?.let {
                        scenes?.put(item.key, it)
                    }
                }
            }

    private fun entryToHue(obj: Map.Entry<String, JsonElement>): HueScene? =
                gson.fromJson(obj.value, HueScene::class.java).apply { id = obj.key }
}