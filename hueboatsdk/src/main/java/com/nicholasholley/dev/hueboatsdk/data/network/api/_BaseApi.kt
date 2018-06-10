package com.nicholasholley.dev.hueboatsdk.data.network.api

interface _BaseApi {
    companion object {
        const val USERNAME_REPLACEMENT = "username"
        const val ID_REPLACEMENT = "id"
        const val BASE_PATH = "/api/{$USERNAME_REPLACEMENT}"
    }
}