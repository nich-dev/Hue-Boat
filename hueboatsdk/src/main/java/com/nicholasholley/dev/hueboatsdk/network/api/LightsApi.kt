package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.HueLight
import com.nicholasholley.dev.hueboatsdk.models.HueLightWrapper
import com.nicholasholley.dev.hueboatsdk.models.HueState
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface LightsApi {

    @GET(ApiPaths.Lights.ALL)
    fun getAll(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueLightWrapper>

    @GET(ApiPaths.Lights.NEW)
    fun getNew(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueLightWrapper>

    @GET(ApiPaths.Lights.SINGLE)
    fun get(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<HueLight>

    @POST(ApiPaths.Lights.ALL)
    fun startNewSearch(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Lights.SINGLE)
    fun update(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
        @Body hueLight: HueLight
    ): Call<List<SimpleResponse>>

    @PUT(ApiPaths.Lights.SINGLE_STATE)
    fun updateState(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
        @Body hueState: HueState
    ): Call<List<SimpleResponse>>

    @DELETE(ApiPaths.Lights.SINGLE)
    fun delete(
        @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
        @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    )
}