package com.nicholasholley.dev.hueboatsdk.models

data class HueAction(
        var address: String,
        var method: String,
        var body: HueActionWrapper
)
