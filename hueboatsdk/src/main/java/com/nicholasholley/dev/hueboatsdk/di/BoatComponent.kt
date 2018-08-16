package com.nicholasholley.dev.hueboatsdk.di

import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.di.modules.ApiModule
import com.nicholasholley.dev.hueboatsdk.di.modules.BaseModule
import com.nicholasholley.dev.hueboatsdk.di.modules.DataModule
import com.nicholasholley.dev.hueboatsdk.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        BaseModule::class,
        NetworkModule::class,
        ApiModule::class,
        DataModule::class
))
interface BoatComponent {
    fun inject(target: Boat)
}