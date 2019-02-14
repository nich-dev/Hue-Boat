package com.nicholasholley.dev.hueboatsdk

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.models.serialization.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Boat(
    var bridgeIp: String,
    var username: String
) {

    private val factory = GsonConverterFactory.create(gson)
    private val loggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    private val client = OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build()
    private val retrofit = Retrofit.Builder()
            .addConverterFactory(factory)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(bridgeIp)
            .build()


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