package com.dukeenergy.etrac.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.nicholasholley.dev.hueboat.R
import com.nicholasholley.dev.hueboat.data.network.upnp.UPnPDeviceFinder
import com.nicholasholley.dev.hueboat.util.rx.SchedulersFacade
import dagger.Module
import dagger.Provides
import io.realm.Realm
import javax.inject.Singleton

/***
 * This Dagger module provide app wide objects.
 */

@Module(includes = arrayOf(
    ViewModelModule::class
))
class AppModule() {

    @Provides
    @Singleton
    fun providesAppContext(app: Application): Context = app

    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences(app.resources.getString(R.string.shared_pref_name), 0)

    @Provides
    @Singleton
    fun provideUPnPDeviceFinder(): UPnPDeviceFinder = UPnPDeviceFinder()

    @Provides
    fun provideSchedulersFacade(): SchedulersFacade = SchedulersFacade()
}