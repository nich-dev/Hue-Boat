package com.nicholasholley.dev.hueboat.data.network.upnp

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
import java.io.StringReader
import java.net.InetAddress
import java.net.MalformedURLException
import java.net.URL
import java.net.UnknownHostException
import java.util.HashMap

import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathFactory

import org.w3c.dom.Document
import org.xml.sax.InputSource
import org.xml.sax.SAXParseException

import android.text.TextUtils

import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * Based on:
 * dgmltn/Android-UPnP-Browser
 */

class UPnPDevice private constructor() : UPnPData {

    var rawUPnP: String? = null
        private set
    var rawXml: String? = null
        private set
    override var location: URL? = null
    var server: String? = null
        private set

    override var mProperties: HashMap<String, String>? = null
    var iconUrl: String? = null
        private set

    override val host: String
        get() = location!!.host

    override val inetAddress: InetAddress
        @Throws(UnknownHostException::class)
        get() = InetAddress.getByName(host)

    override val friendlyName: String?
        get() = mProperties!![FRIENDLY_NAME]

    // Special case for SONOS: remove the leading ip address from the friendly name
    // "192.168.1.123 - Sonos PLAY:1" => "Sonos PLAY:1"
    override val scrubbedFriendlyName: String?
        get() {
            var friendlyName: String? = mProperties!!["xml_friendly_name"]
            if (friendlyName != null && friendlyName.startsWith(host + " - ")) {
                friendlyName = friendlyName.substring(host.length + 3)
            }

            return friendlyName
        }

    ////////////////////////////////////////////////////////////////////////////////
    // UPnP Specification Downloading / Parsing
    ////////////////////////////////////////////////////////////////////////////////

    @Transient private val mClient = OkHttpClient()

    override fun generateIconUrl(): String? {
        var path = mProperties!!["xml_icon_url"]
        if (TextUtils.isEmpty(path)) {
            return null
        }
        if (path?.startsWith("/") ?: false) {
            path = path?.substring(1)
        }
        iconUrl = location!!.protocol + "://" + location!!.host + ":" + location!!.port + "/" + path
        return iconUrl
    }

    @Throws(Exception::class)
    override fun downloadSpecs() {
        val request = Request.Builder()
                .url(location!!)
                .build()

        val response = mClient.newCall(request).execute()
        if (!response.isSuccessful) {
            throw IOException("Unexpected code " + response)
        }

        rawXml = response.body()!!.string()

        val dbf = DocumentBuilderFactory.newInstance()
        val db = dbf.newDocumentBuilder()
        val source = InputSource(StringReader(rawXml!!))
        val doc: Document
        try {
            doc = db.parse(source)
        } catch (e: SAXParseException) {
            return
        }

        val xPath = XPathFactory.newInstance().newXPath()

        mProperties!!.put("xml_icon_url", xPath.compile("//icon/url").evaluate(doc))
        generateIconUrl()
        mProperties!!.put(FRIENDLY_NAME, xPath.compile("//friendlyName").evaluate(doc))
    }

    companion object {

        ////////////////////////////////////////////////////////////////////////////////
        // UPnP Response Parsing
        ////////////////////////////////////////////////////////////////////////////////
        val FRIENDLY_NAME = "xml_friendly_name"

        fun getInstance(raw: String): UPnPDevice? {
            val parsed = parseRaw(raw)
            try {
                val device = UPnPDevice()
                device.rawUPnP = raw
                device.mProperties = parsed
                device.location = URL(parsed["upnp_location"])
                device.server = parsed["upnp_server"]
                return device
            } catch (e: MalformedURLException) {
                e.printStackTrace()
                return null
            }

        }

        private fun parseRaw(raw: String): HashMap<String, String> {
            val results = HashMap<String, String>()
            for (line in raw.split("\r\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
                val colon = line.indexOf(":")
                if (colon != -1) {
                    val key = line.substring(0, colon).trim { it <= ' ' }.toLowerCase()
                    val value = line.substring(colon + 1).trim { it <= ' ' }
                    results.put("upnp_" + key, value)
                }
            }
            return results
        }
    }
}