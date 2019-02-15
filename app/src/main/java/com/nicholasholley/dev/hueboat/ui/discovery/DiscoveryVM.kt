package com.nicholasholley.dev.hueboat.ui.discovery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nicholasholley.dev.hueboat.util.ext.log
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
    private val devices = mutableListOf<UPnPData>()
    val uPnPDevices: MutableLiveData<List<UPnPData>> = MutableLiveData()
    val fragmentState: MutableLiveData<DiscoveryFragment.State> = MutableLiveData()

    private val deviceFinder: UPnPDeviceFinder = UPnPDeviceFinder(this)

    init {
        uPnPDevices.value = devices.toList()
        fragmentState.value = DiscoveryFragment.State.SEARCH
        deviceFinder.observe()
    }

    override fun onDeviceFound(device: UPnPDevice) {
        devices.add(device)
        uPnPDevices.postValue(devices.toList())
        fragmentState.postValue(DiscoveryFragment.State.SELECT)
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