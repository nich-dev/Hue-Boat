package com.nicholasholley.dev.hueboat.di.modules

import com.nicholasholley.dev.hueboat.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/***
 * This Dagger module provides each activity to the application.
 * AppInjector will handle the lateinit injection in your activity only if you follow the following...
 * The activity will need "HasSupportFragmentInjector" for it's fragment injection.
 *     - Pass: @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
 */

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentModule::class))
    abstract fun contributeDashboardBActivity(): MainActivity
}