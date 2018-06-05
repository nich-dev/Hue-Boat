package com.nicholasholley.dev.hueboat.ui.discovery

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.nicholasholley.dev.hueboat.data.network.upnp.UPnPData
import com.nicholasholley.dev.hueboat.data.network.upnp.UPnPDeviceFinder
import com.nicholasholley.dev.hueboat.util.log.Log
import com.nicholasholley.dev.hueboat.util.rx.SchedulersFacade
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DiscoveryVM @Inject constructor(
        private val schedulersFacade: SchedulersFacade
): ViewModel() {
    val uPnPDevices: MutableLiveData<MutableList<UPnPData>> = MutableLiveData()

    fun doWatch() {
        if (uPnPDevices.value == null) uPnPDevices.value = mutableListOf()
        else uPnPDevices.value?.clear()
        UPnPDeviceFinder().observe()
                .subscribeOn(Schedulers.io())
                .filter {
                    try {
                        it.downloadSpecs()
                    } catch (e: Exception) {
                        Log.d(e.toString())
                    }
                    it.friendlyName?.contains("Philips hue") ?: false
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //TODO: Get working
                    uPnPDevices.value?.add(it)
                }
    }
}