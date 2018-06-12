package com.nicholasholley.dev.hueboatsdk.di.modules

import com.nicholasholley.dev.hueboatsdk.data.network.api.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Provides
    @Singleton
    fun providesGrouppi(retrofit: Retrofit): GroupsApi {
        return retrofit.create(GroupsApi::class.java)
    }
    @Provides
    @Singleton
    fun providesLightApi(retrofit: Retrofit): LightsApi {
        return retrofit.create(LightsApi::class.java)
    }
    @Provides
    @Singleton
    fun providesRuleApi(retrofit: Retrofit): RuleApi {
        return retrofit.create(RuleApi::class.java)
    }
    @Provides
    @Singleton
    fun providesScenesApi(retrofit: Retrofit): ScenesApi {
        return retrofit.create(ScenesApi::class.java)
    }
    @Provides
    @Singleton
    fun providesScheduleApi(retrofit: Retrofit): ScheduleApi {
        return retrofit.create(ScheduleApi::class.java)
    }
    @Provides
    @Singleton
    fun providesSenorApi(retrofit: Retrofit): SensorApi {
        return retrofit.create(SensorApi::class.java)
    }
}