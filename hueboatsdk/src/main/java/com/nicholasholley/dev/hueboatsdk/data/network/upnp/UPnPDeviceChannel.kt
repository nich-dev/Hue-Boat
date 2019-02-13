package com.nicholasholley.dev.hueboatsdk.data.network.upnp

import java.lang.Exception

interface UPnPDeviceChannel {
    fun onDeviceFound(device: UPnPDevice)
    fun onDeviceChannelComplete()
    fun onDeviceChannelError(e: Exception)
}
