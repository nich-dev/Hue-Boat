package com.nicholasholley.dev.hueboatsdk.data.models

data class HueConfiguration(
        var id:                 Long = 0L,
        var name:               String? = null,
        var apiversion:         String? = null,
        var swversion:          String? = null,
        var proxyaddress:       String? = null,
        var ipaddress:          String? = null,
        var mac:                String? = null,
        var netmask:            String? = null,
        var gateway:            String? = null,
        var UTC:                String? = null,
        var localtime:          String? = null,
        var timezone:           String? = null,
        var modelid:            String? = null,
        var bridgeid:           String? = null,
        var replacesbridgeid:   String? = null,
        var datastoreversion:   String? = null,
        var starterkitid:       String? = null,
        var proxyport:          Int? = null,
        var zigbeechannel:      Int? = null,
        var factorynew:         Boolean? = null
)
