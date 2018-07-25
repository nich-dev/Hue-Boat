package com.nicholasholley.dev.hueboat.ui.discovery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.nicholasholley.dev.hueboat.util.log.Log
import com.nicholasholley.dev.hueboat.util.rx.SchedulersFacade
import com.nicholasholley.dev.hueboatsdk.data.network.upnp.UPnPData
import com.nicholasholley.dev.hueboatsdk.data.network.upnp.UPnPDeviceFinder
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DiscoveryVM @Inject constructor(
        private val schedulersFacade: SchedulersFacade
): ViewModel() {
    private val disposable: Disposable
    val uPnPDevices: MutableLiveData<MutableList<UPnPData>> = MutableLiveData()
    val fragmentState: MutableLiveData<DiscoveryFragment.State> = MutableLiveData()

    init {
        fragmentState.value = DiscoveryFragment.State.SEARCH
        uPnPDevices.value = mutableListOf()
        disposable = UPnPDeviceFinder().observe()
                .subscribeOn(schedulersFacade.io())
                .filter {
                    try {
                        it.downloadSpecs()
                    } catch (e: Exception) {
                        Log.d(e.toString())
                    }
                    it.friendlyName?.contains("Philips hue") ?: false
                }
                .observeOn(schedulersFacade.ui())
                .subscribe {
                    uPnPDevices.value?.add(it)
                    //LiveData does not update unless object changes.
                    //Could write custom LiveData implementation also.
                    uPnPDevices.value = uPnPDevices.value
                }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}