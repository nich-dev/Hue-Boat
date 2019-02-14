package com.nicholasholley.dev.hueboatsdk.network.upnp

import java.lang.Exception

interface UPnPDeviceChannel {
    fun onDeviceFound(device: UPnPDevice)
    fun onDeviceChannelComplete()
    fun onDeviceChannelError(e: Exception)
}
