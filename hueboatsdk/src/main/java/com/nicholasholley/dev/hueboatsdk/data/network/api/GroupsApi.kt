package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueGroup
import com.nicholasholley.dev.hueboatsdk.data.models.HueGroupWrapper
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface GroupsApi {
    @GET(ApiPaths.Groups.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueGroupWrapper>

    @POST(ApiPaths.Groups.ALL)
    fun create(
            @Body group: HueGroup,
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<SimpleResponse>

    @GET(ApiPaths.Groups.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<HueGroup>

    @PUT(ApiPaths.Groups.SINGLE)
    fun update(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueGroup: HueGroup
    ): Call<List<SimpleResponse>>

    @PUT(ApiPaths.Groups.SINGLE_STATE)
    fun updateState(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueState: HueState
    ): Call<List<SimpleResponse>>

    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<SimpleResponse>
}