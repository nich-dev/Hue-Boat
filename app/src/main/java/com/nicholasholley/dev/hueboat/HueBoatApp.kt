package com.nicholasholley.dev.hueboat

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class HueBoatApp: Application() {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var mServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        super.onCreate()
    }
}