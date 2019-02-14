package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface GroupsApi {
    @GET(ApiPaths.Groups.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueGroupWrapper>

    @POST(ApiPaths.Groups.ALL)
    fun post(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body group: HueGroup
    ): Deferred<SuccessWrapper>

    @GET(ApiPaths.Groups.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueGroup>

    @PUT(ApiPaths.Groups.SINGLE)
    fun put(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueGroup: HueGroup
    ): Deferred<List<SimpleResponse>>

    @PUT(ApiPaths.Groups.SINGLE_STATE)
    fun putState(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueState: HueState
    ): Deferred<List<SimpleResponse>>

    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<List<SimpleResponse>>
}