package com.nicholasholley.dev.hueboat

import android.app.Activity
import android.app.Application
import android.app.Service
import com.nicholasholley.dev.hueboat.di.AppInjector
import com.nicholasholley.dev.hueboat.util.log.FirebaseTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import net.ypresto.timbertreeutils.ThrowErrorTree
import timber.log.Timber
import javax.inject.Inject

class HueBoatApp: Application(), HasActivityInjector, HasServiceInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var mServiceInjector: DispatchingAndroidInjector<Service>

    override fun onCreate() {
        AppInjector.init(this)
        super.onCreate()
        if (BuildConfig.BUILD_TYPE == "debug"){
            Timber.plant(Timber.DebugTree())
            Timber.plant(ThrowErrorTree())
        } else {
            Timber.plant(FirebaseTree())
        }
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return mServiceInjector
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    companion object {
        private const val STETHO_LIMIT = 1000L
        private const val CLEAR_REALM = true
    }
}