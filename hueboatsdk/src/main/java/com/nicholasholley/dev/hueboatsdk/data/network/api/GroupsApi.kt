package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueGroup
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueGroupWrapper
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface GroupsApi {
    @GET(GROUPS_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueGroupWrapper>

    @POST(GROUPS_ALL)
    fun create(
            @Body group: HueGroup,
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<SimpleResponse>

    @GET(GROUPS_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<HueGroup>

    @PUT(GROUPS_SINGLE)
    fun update(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueGroup: HueGroup
    ): Call<List<SimpleResponse>>

    @PUT(GROUPS_SINGLE_STATE)
    fun updateState(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueState: HueState
    ): Call<List<SimpleResponse>>

    fun delete(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<SimpleResponse>

    companion object {
        private const val GROUPS_ALL = "$BASE_PATH/groups"
        private const val GROUPS_NEW = "$GROUPS_ALL/new"
        private const val GROUPS_SINGLE = "$GROUPS_ALL/{$ID_REPLACEMENT}"
        private const val GROUPS_SINGLE_STATE = "$GROUPS_ALL/{$ID_REPLACEMENT}/state"
    }
}