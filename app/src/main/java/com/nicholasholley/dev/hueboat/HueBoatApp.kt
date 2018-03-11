package com.nicholasholley.dev.hueboat

import android.app.Activity
import android.app.Application
import android.app.Service
import com.dukeenergy.etrac.di.AppInjector
import com.facebook.stetho.Stetho
import com.nicholasholley.dev.hueboat.util.log.FirebaseTree
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import net.ypresto.timbertreeutils.ThrowErrorTree
import timber.log.Timber
import javax.inject.Inject

class HueBoatApp: Application(), HasActivityInjector, HasServiceInjector {
    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var mServiceInjector: DispatchingAndroidInjector<Service>


    override fun onCreate() {
        AppInjector.init(this)
        super.onCreate()
        Realm.init(this)
        if (CLEAR_REALM) clearRealm()
        if (BuildConfig.BUILD_TYPE == "debug"){
            Timber.plant(Timber.DebugTree())
            Timber.plant(ThrowErrorTree())
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(
                                    RealmInspectorModulesProvider.builder(this).withLimit(STETHO_LIMIT).build()
                            ).build());
        } else {
            Timber.plant(FirebaseTree())
        }
    }

    private fun clearRealm(){
        RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build().let { Realm.setDefaultConfiguration(it) }
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