package com.nicholasholley.dev.hueboatsdk.data.models

data class HueSchedule(
        var id:             Long = 0L,
        var name:           String? = null,
        var description:    String? = null,
        var localtime:      String? = null,
        var status:         String? = null,
        var autodelete:     Boolean = true,
        var recycle:        Boolean = false,
        var command:        HueCommand? = null
)

data class HueCommand(
        var id:             Long = 0L,
        var address:        String? = null,
        var method:         String? = null,
        var body:           HueCommandWrapper? = null
)
