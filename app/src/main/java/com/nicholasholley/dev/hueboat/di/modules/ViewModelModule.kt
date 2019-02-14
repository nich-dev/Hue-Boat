package com.nicholasholley.dev.hueboat.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nicholasholley.dev.hueboat.di.ViewModelFactory
import com.nicholasholley.dev.hueboat.ui.MainVM
import com.nicholasholley.dev.hueboat.ui.discovery.DiscoveryVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/***
 * This Dagger module binds each view model. The factory is needed for google's vm implementation.
 */

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelMap(MainVM::class)
    abstract fun bindMainVM(vm: MainVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelMap(DiscoveryVM::class)
    abstract fun bindDiscoveryVM(vm: DiscoveryVM): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}