package com.nicholasholley.dev.hueboatsdk.models

data class HueRule(
        var id:             String? = null,
        var name:           String? = null,
        var lasttriggered:  String? = null,
        var creationtime:   String? = null,
        var owner:          String? = null,
        var status:         String? = null,
        var conditions:     List<HueCondition> = listOf(),
        var actions:        List<HueAction> = listOf()
)

data class HueCondition(
        var address:        String? = null,
        var operator:       String? = null,
        var value:          String? = null
)
