package com.nicholasholley.dev.hueboat.ui.discovery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicholasholley.dev.hueboat.util.rx.SchedulersFacade
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPData
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDevice
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDeviceChannel
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDeviceFinder
import java.lang.Exception
import javax.inject.Inject

class DiscoveryVM @Inject constructor(
        private val schedulersFacade: SchedulersFacade
): ViewModel(), UPnPDeviceChannel {
    val uPnPDevices: MutableLiveData<MutableList<UPnPData>> = MutableLiveData()
    val fragmentState: MutableLiveData<DiscoveryFragment.State> = MutableLiveData()

    private val deviceFinder: UPnPDeviceFinder = UPnPDeviceFinder(this)

    init {
        fragmentState.value = DiscoveryFragment.State.SEARCH
        uPnPDevices.value = mutableListOf()
        deviceFinder.observe()
    }

    override fun onDeviceFound(device: UPnPDevice) {
        uPnPDevices.value?.add(device)
        uPnPDevices.value = uPnPDevices.value
    }

    override fun onDeviceChannelComplete() {

    }

    override fun onDeviceChannelError(e: Exception) {

    }

    override fun onCleared() {
        super.onCleared()
        deviceFinder.close()
    }
}