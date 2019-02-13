package com.nicholasholley.dev.hueboatsdk

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nicholasholley.dev.hueboatsdk.data.models.*
import com.nicholasholley.dev.hueboatsdk.data.models.serialization.*

class Boat(
    val bridgeIp: String,
    var username: String
) {
    companion object {
        private val baseGson = Gson()
        val gson = GsonBuilder().apply {
            registerTypeAdapter(HueGroupWrapper::class.java, GroupSerializer(baseGson))
            registerTypeAdapter(HueActionWrapper::class.java, ActionSerializer(baseGson))
            registerTypeAdapter(HueLightWrapper::class.java, LightSerializer(baseGson))
            registerTypeAdapter(HueRuleWrapper::class.java, RuleSerializer(baseGson))
            registerTypeAdapter(HueSceneWrapper::class.java, SceneSerializer(baseGson))
            registerTypeAdapter(HueScheduleWrapper::class.java, ScheduleSerializer(baseGson))
            registerTypeAdapter(HueSensorWrapper::class.java, SensorSerializer(baseGson))
            registerTypeAdapter(HueLightStateWrapper::class.java, LightStateSerializer(baseGson))
            registerTypeAdapter(HueCommandWrapper::class.java, CommandSerializer(baseGson))
        }.create()
    }
}