package com.nicholasholley.dev.hueboat.di.modules

import com.nicholasholley.dev.hueboat.ui.discovery.DiscoveryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/***
 * This Dagger module provides each fragment to the application.
 * AppInjector will handle the lateinit injection in your fragmentif you follow the following...
 * The fragment will need to implement "SetToInject"
 */

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeDiscoveryFragment(): DiscoveryFragment
}