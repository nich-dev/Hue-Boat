package com.nicholasholley.dev.hueboatsdk.di.modules

import com.nicholasholley.dev.hueboatsdk.data.network.upnp.UPnPDeviceFinder
import com.nicholasholley.dev.hueboatsdk.util.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule {
    @Provides
    @Singleton
    fun provideUPnPDeviceFinder(): UPnPDeviceFinder = UPnPDeviceFinder()

    @Provides
    fun provideSchedulersFacade(): SchedulersFacade = SchedulersFacade()
}