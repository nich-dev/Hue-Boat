package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.HueLight
import com.nicholasholley.dev.hueboatsdk.models.HueLightWrapper
import com.nicholasholley.dev.hueboatsdk.models.HueState
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface LightsApi {

    @GET(ApiPaths.Lights.ALL)
    fun getAll(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueLightWrapper>

    @GET(ApiPaths.Lights.NEW)
    fun getNew(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueLightWrapper>

    @GET(ApiPaths.Lights.SINGLE)
    fun get(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueLight>

    @POST(ApiPaths.Lights.ALL)
    fun startNewSearch(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<List<SimpleResponse>>

    @PUT(ApiPaths.Lights.SINGLE)
    fun put(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id:String,
        @Body hueLight: HueLight
    ): Deferred<List<SimpleResponse>>

    @PUT(ApiPaths.Lights.SINGLE_STATE)
    fun putState(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
        @Body hueState: HueState
    ): Deferred<List<SimpleResponse>>

    @DELETE(ApiPaths.Lights.SINGLE)
    fun delete(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    )
}