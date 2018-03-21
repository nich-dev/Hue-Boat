package com.nicholasholley.dev.hueboat.di.modules

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.data.models.serialization.LightSerializer
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueLightWrapper
import com.nicholasholley.dev.hueboat.data.network.json.RealmExclusionStrategy
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
    fun provideBaseUrlString(app: Application): String {
        return app.getString(R.string.base_url)
    }

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
    fun provideLightSerializer(@Named(WRAPPER_GSON) gson: Gson): LightSerializer {
        return LightSerializer(gson)
    }

    @Provides
    @Singleton
    @Named(BASE_GSON)
    fun provideBaseGson(lightSerializer: LightSerializer): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setExclusionStrategies(RealmExclusionStrategy())
                .registerTypeAdapter(HueLightWrapper::class.java, lightSerializer)
                .create()
    }

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