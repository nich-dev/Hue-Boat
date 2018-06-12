package com.dukeenergy.etrac.di

import android.app.Application
import com.dukeenergy.etrac.di.modules.*
import com.nicholasholley.dev.hueboat.HueBoatApp
import com.nicholasholley.dev.hueboat.di.modules.ActivityModule
import com.nicholasholley.dev.hueboat.di.modules.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This Dagger component uses dagger 2.13 features to Build the dependency tree.
 * Inject the app to instantiate the application builder.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        DataModule::class
))
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: HueBoatApp)
}