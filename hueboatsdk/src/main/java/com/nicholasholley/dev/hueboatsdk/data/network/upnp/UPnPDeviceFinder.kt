package com.nicholasholley.dev.hueboatsdk.data.network.upnp

/*
 * Copyright (C) 2015 Doug Melton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException
import java.net.DatagramPacket
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.MulticastSocket
import java.net.NetworkInterface
import java.net.SocketAddress
import java.util.Collections
import java.util.regex.Pattern
import com.nicholasholley.dev.hueboatsdk.util.d

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext

/**
 * Based on:
 * dgmltn/Android-UPnP-Browser
 *
 * Which is based on:
 * https://github.com/heb-dtc/SSDPDiscovery/blob/master/src/main/java/com/flo/upnpdevicedetector/UPnPDeviceFinder.java
 *
 * This class will find all UPnP devices with a response code of "IpBridge" as specified by the philips hue api guide
 * RxJava is exchanged for Kotlin Coroutines
 */
class UPnPDeviceFinder @JvmOverloads constructor(
        private val deviceChannel: UPnPDeviceChannel? = null,
        IPV4: Boolean = true
) : CoroutineScope {

    var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val mInetDeviceAdr: InetAddress

    private var mSock: UPnPSocket? = null

    init {
        mInetDeviceAdr = getDeviceLocalIP(IPV4)!!
        "IP is: $mInetDeviceAdr".d()

        try {
            mSock = UPnPSocket(mInetDeviceAdr)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    private fun open() {
        job = Job()
    }

    fun close() {
        job.cancel()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun observe() {
        if (job.isCancelled) open()
        /**
         * @param emitter the safe emitter instance, never null
         * @throws Exception on error
         */
        if (mSock == null) {
            deviceChannel?.onDeviceChannelError(Exception("Socket is null"))
            return
        }
        val handler = CoroutineExceptionHandler { _, _ ->
            mSock!!.close()
            deviceChannel?.onDeviceChannelComplete()
        }
        launch(handler) {
            runBlocking {
                // Broadcast SSDP search messages
                mSock!!.sendMulticastMsg()
                val source = produce<UPnPDevice> {
                    // Listen to responses from network until the socket timeout
                    while (true) {
                        "wait for dev. response".d()
                        val dp = mSock!!.receiveMulticastMsg()
                        var receivedString = String(dp.data)
                        receivedString = receivedString.substring(0, dp.length)
                        "found dev: $receivedString".d()
                        if (receivedString.contains("IpBridge", true)){
                            val device = UPnPDevice.getInstance(receivedString)
                            if (device != null) {
                                send(device)
                            }
                        }
                    }
                }
                source.consumeEach { device ->
                    deviceChannel?.onDeviceFound(device)
                }
            }
        }
        try {

        } catch (e: IOException) {
            //sock timeout will get us out of the loop

        }
    }

    ////////////////////////////////////////////////////////////////////////////////
    // UPnPSocket
    ////////////////////////////////////////////////////////////////////////////////

    private class UPnPSocket @Throws(IOException::class)
    internal constructor(deviceIp: InetAddress) {

        private val mMulticastGroup: SocketAddress
        private val mMultiSocket: MulticastSocket

        init {
            mMulticastGroup = InetSocketAddress(MULTICAST_ADDRESS, PORT)
            mMultiSocket = MulticastSocket(InetSocketAddress(deviceIp, 0))

            mMultiSocket.soTimeout = MSG_TIMEOUT
        }

        @Throws(IOException::class)
        fun sendMulticastMsg() {
            val ssdpMsg = buildSSDPSearchString()

            val dp = DatagramPacket(ssdpMsg.toByteArray(), ssdpMsg.length, mMulticastGroup)
            mMultiSocket.send(dp)
        }

        @Throws(IOException::class)
        fun receiveMulticastMsg(): DatagramPacket {
            val buf = ByteArray(2048)
            val dp = DatagramPacket(buf, buf.size)

            mMultiSocket.receive(dp)
            return dp
        }

        /**
         * Closing the Socket.
         */
        fun close() {
            mMultiSocket.close()
        }
    }

    companion object {
        const val MULTICAST_ADDRESS = "239.255.255.250"

        const val PORT = 1900

        const val MAX_REPLY_TIME = 60
        const val MSG_TIMEOUT = MAX_REPLY_TIME * 1000 + 1000

        ////////////////////////////////////////////////////////////////////////////////
        // Utils
        ////////////////////////////////////////////////////////////////////////////////

        const val NEWLINE = "\r\n"

        private fun buildSSDPSearchString(): String {
            val content = StringBuilder()

            content.append("M-SEARCH * HTTP/1.1").append(NEWLINE)
            content.append("Host: ${MULTICAST_ADDRESS}:${PORT}").append(NEWLINE)
            content.append("Man:\"ssdp:discover\"").append(NEWLINE)
            content.append("MX: " + MAX_REPLY_TIME).append(NEWLINE)
            content.append("ST: upnp:rootdevice").append(NEWLINE)
            content.append(NEWLINE)

            return content.toString()
        }

        private fun getDeviceLocalIP(useIPv4: Boolean): InetAddress? {

            try {
                val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
                for (intf in interfaces) {
                    val addrs = Collections.list(intf.inetAddresses)
                    for (addr in addrs) {
                        if (!addr.isLoopbackAddress) {
                            val sAddr = addr.hostAddress.toUpperCase()
                            val isIPv4 = isIPv4Address(sAddr)
                            if (useIPv4) {
                                if (isIPv4) {
                                    return addr
                                }
                            } else {
                                if (!isIPv4) {
                                    //int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                                    //return delim<0 ? sAddr : sAddr.substring(0, delim);
                                    return addr
                                }
                            }
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.message.d()
            }
            // for now eat exceptions
            return null
        }

        // From Apache InetAddressUtils
        // https://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/conn/util/InetAddressUtils.html
        private val IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$")

        private fun isIPv4Address(input: String): Boolean {
            return IPV4_PATTERN.matcher(input).matches()
        }
    }
}
