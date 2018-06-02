package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.models.HueLight
import com.nicholasholley.dev.hueboat.data.models.HueState
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueLightWrapper
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface LightsApi : _BaseApi {

    @GET(LIGHTS_ALL)
    fun getAll(
        @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueLightWrapper>

    @GET(LIGHTS_NEW)
    fun getNew(
        @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueLightWrapper>

    @GET(LIGHTS_SINGLE)
    fun get(
        @Path(value = USERNAME_REPLACEMENT) username: String?,
        @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<HueLight>

    @POST(LIGHTS_ALL)
    fun startNewSearch(
        @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<SimpleResponse>

    @PUT(LIGHTS_SINGLE)
    fun update(
        @Path(value = USERNAME_REPLACEMENT) username: String?,
        @Path(value = ID_REPLACEMENT) id: Long = 0L,
        @Body hueLight: HueLight
    ): Call<List<SimpleResponse>>

    @PUT(LIGHTS_SINGLE_STATE)
    fun updateState(
        @Path(value = USERNAME_REPLACEMENT) username: String?,
        @Path(value = ID_REPLACEMENT) id: Long = 0L,
        @Body hueState: HueState
    ): Call<List<SimpleResponse>>

    @DELETE(LIGHTS_SINGLE)
    fun delete(
        @Path(value = USERNAME_REPLACEMENT) username: String?,
        @Path(value = ID_REPLACEMENT) id: Long = 0L
    )

    companion object {
        private const val LIGHTS_ALL = "$BASE_PATH/lights"
        private const val LIGHTS_NEW = "$LIGHTS_ALL/new"
        private const val LIGHTS_SINGLE = "$LIGHTS_ALL/{$ID_REPLACEMENT}"
        private const val LIGHTS_SINGLE_STATE = "$LIGHTS_ALL/{$ID_REPLACEMENT}/state"
    }
}