package com.nicholasholley.dev.hueboatsdk.di.modules

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nicholasholley.dev.hueboatsdk.data.models.serialization.*
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.*
import com.nicholasholley.dev.hueboatsdk.data.network.json.RealmExclusionStrategy
import com.nicholasholley.dev.hueboatsdk.util.NETWORK_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    @Named(BASE_URL)
    fun provideBaseUrlString(): String = NETWORK_URL

    @Provides
    @Singleton
    @Named(WRAPPER_GSON)
    fun provideWrapperGson(): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setExclusionStrategies(RealmExclusionStrategy())
                .create()
    }

    @Provides
    @Singleton
    fun provideRuleSerializer(@Named(WRAPPER_GSON) gson: Gson): RuleSerializer = RuleSerializer(gson)

    @Provides
    @Singleton
    fun provideLightSerializer(@Named(WRAPPER_GSON) gson: Gson): LightSerializer = LightSerializer(gson)

    @Provides
    @Singleton
    fun provideGroupSerializer(@Named(WRAPPER_GSON) gson: Gson): GroupSerializer = GroupSerializer(gson)

    @Provides
    @Singleton
    fun provideSceneSerializer(@Named(WRAPPER_GSON) gson: Gson): SceneSerializer = SceneSerializer(gson)

    @Provides
    @Singleton
    fun provideScheduleSerializer(@Named(WRAPPER_GSON) gson: Gson): ScheduleSerializer = ScheduleSerializer(gson)

    @Provides
    @Singleton
    fun provideSensorSerializer(@Named(WRAPPER_GSON) gson: Gson): SensorSerializer = SensorSerializer(gson)

    @Provides
    @Singleton
    @Named(BASE_GSON)
    fun provideBaseGson(
            groupSerializer: GroupSerializer, 
            lightSerializer: LightSerializer,
            ruleSerializer: RuleSerializer,
            sceneSerializer: SceneSerializer,
            scheduleSerializer: ScheduleSerializer,
            sensorSerializer: SensorSerializer
    ): Gson = GsonBuilder().apply {
            excludeFieldsWithoutExposeAnnotation()
            setExclusionStrategies(RealmExclusionStrategy())
            registerTypeAdapter(HueGroupWrapper::class.java, groupSerializer)
            registerTypeAdapter(HueLightWrapper::class.java, lightSerializer)
            registerTypeAdapter(HueRuleWrapper::class.java, ruleSerializer)
            registerTypeAdapter(HueSceneWrapper::class.java, sceneSerializer)
            registerTypeAdapter(HueScheduleWrapper::class.java, scheduleSerializer)
            registerTypeAdapter(HueSensorWrapper::class.java, sensorSerializer)
    }.create()

    @Provides
    @Singleton
    fun provideGsonConverter(@Named(BASE_GSON) gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideLoginEmpRetrofit(factory: Converter.Factory,
                                @Named(BASE_URL) baseUrl: String,
                                okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(factory)
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build()
    }

    companion object {
        const val BASE_URL = "url.base"
        const val BASE_GSON = "gson.base"
        const val WRAPPER_GSON = "gson.wrapper"
        const val TIMEOUT_TIME = 30L
    }
}