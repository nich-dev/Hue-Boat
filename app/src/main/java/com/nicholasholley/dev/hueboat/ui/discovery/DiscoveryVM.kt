package com.nicholasholley.dev.hueboat.ui.discovery

import androidx.lifecycle.MutableLiveData
import com.nicholasholley.dev.hueboat.ui.common.CoroutineViewModel
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPData
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDevice
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDeviceChannel
import com.nicholasholley.dev.hueboatsdk.network.upnp.UPnPDeviceFinder
import javax.inject.Inject

class DiscoveryVM @Inject constructor(): CoroutineViewModel(), UPnPDeviceChannel {
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