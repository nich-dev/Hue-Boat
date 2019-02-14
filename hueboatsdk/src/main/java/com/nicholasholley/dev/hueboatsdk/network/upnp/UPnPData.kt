package com.nicholasholley.dev.hueboatsdk.network.upnp

import java.net.InetAddress
import java.net.URL
import java.util.HashMap

interface UPnPData {
    val friendlyName: String?
    val scrubbedFriendlyName: String?
    val host: String
    val inetAddress: InetAddress
    var location: URL?
    var mProperties: HashMap<String, String>?
    fun generateIconUrl(): String?
    fun downloadSpecs()
}