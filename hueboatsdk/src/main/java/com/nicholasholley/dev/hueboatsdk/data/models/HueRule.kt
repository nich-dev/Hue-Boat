package com.nicholasholley.dev.hueboatsdk.data.models

data class HueRule(
        var id:         Long = 0L,
        var name:           String? = null,
        var lasttriggered:  String? = null,
        var creationtime:   String? = null,
        var owner:          String? = null,
        var status:         String? = null,
        var conditions:     List<HueCondition> = listOf(),
        var actions:        List<HueAction> = listOf()
)

data class HueCondition(
        var id:             Long = 0L,
        var address:        String? = null,
        var operator:       String? = null,
        var value:          String? = null
)
