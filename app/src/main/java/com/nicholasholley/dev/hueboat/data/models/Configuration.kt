package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity

@Entity(primaryKeys = ["ipaddress", "mac"])
data class Configuration(
        var ipaddress:          String,
        var mac:                String,
        var username:           String,
        var name:               String? = null,
        var apiversion:         String? = null,
        var swversion:          String? = null,
        var proxyaddress:       String? = null,
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

@Dao
abstract class ConfigurationDao : BaseDao<Configuration>
