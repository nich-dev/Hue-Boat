package com.nicholasholley.dev.hueboat.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.nicholasholley.dev.hueboat.HueBoatApp
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector


/**
 * This helper class will automatically inject your activity or fragment based on their lifecycles
 * if they implement the correct interfaces.
 */
class AppInjector {
    companion object {
        fun init(app: HueBoatApp) {
            DaggerAppComponent.builder()
                .application(app)
                .build().inject(app)
            app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                        handleActivity(activity)
                    }
                    override fun onActivityStarted(activity: Activity) { }
                    override fun onActivityResumed(activity: Activity) { }
                    override fun onActivityPaused(activity: Activity) { }
                    override fun onActivityStopped(activity: Activity) { }
                    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) { }
                    override fun onActivityDestroyed(activity: Activity) { }
                })
        }

        private fun handleActivity(activity: Activity) {
            if (activity is HasSupportFragmentInjector) {
                AndroidInjection.inject(activity)
            }
            if (activity is FragmentActivity) {
                activity.getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(fm: FragmentManager, f: Fragment,
                                                           savedInstanceState: Bundle?) {
                                if (f is MarkForInjection) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true)
            }
        }
    }
}