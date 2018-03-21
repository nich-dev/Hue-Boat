package com.nicholasholley.dev.hueboat.di.modules

import com.nicholasholley.dev.hueboat.data.network.api.LightsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesLightApi(retrofit: Retrofit): LightsApi {
        return retrofit.create(LightsApi::class.java)
    }
}