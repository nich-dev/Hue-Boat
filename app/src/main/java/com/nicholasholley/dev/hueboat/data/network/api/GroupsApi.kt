package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.models.wrapper.HueGroupWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupsApi {
    @GET(GROUPS_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueGroupWrapper>

    companion object {
        private const val USERNAME_REPLACEMENT = "username"
        private const val ID_REPLACEMENT = "id"

        private const val GROUPS_ALL = "/api/{$USERNAME_REPLACEMENT}/groups"
        private const val GROUPS_NEW = "$GROUPS_ALL/new"
        private const val GROUPS_SINGLE = "$GROUPS_ALL/{$ID_REPLACEMENT}"
        private const val GROUPS_SINGLE_STATE = "$GROUPS_ALL/{$ID_REPLACEMENT}/state"
    }
}