package com.nicholasholley.dev.hueboatsdk.data.models

data class HueAction(
        var address: String,
        var method: String,
        var body: HueActionWrapper
)
