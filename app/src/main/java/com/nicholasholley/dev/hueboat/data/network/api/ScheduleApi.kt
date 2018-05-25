package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ScheduleApi {
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
        private const val USERNAME_REPLACEMENT = "username"
        private const val ID_REPLACEMENT = "id"

        private const val SCHEDULES_ALL = "/api/{$USERNAME_REPLACEMENT}/schedules"
        private const val SCHEDULES_NEW = "$SCHEDULES_ALL/new"
        private const val SCHEDULES_SINGLE = "$SCHEDULES_ALL/{$ID_REPLACEMENT}"
        private const val SCHEDULES_SINGLE_STATE = "$SCHEDULES_ALL/{$ID_REPLACEMENT}/schedules"
    }
}