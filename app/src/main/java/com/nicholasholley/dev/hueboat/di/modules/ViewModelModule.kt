package com.dukeenergy.etrac.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.dukeenergy.etrac.di.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/***
 * This Dagger module binds each view model. The factory is needed for google's vm implementation.
 */

@Module
abstract class ViewModelModule {
    /***
    @Binds
    @IntoMap
    @ViewModelMap(VMExample::class)
    abstract fun bindVMExample(vm: VMExample): ViewModel
    */


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}