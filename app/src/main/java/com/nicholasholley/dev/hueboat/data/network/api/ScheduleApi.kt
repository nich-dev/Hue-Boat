package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.models.HueSchedule
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueScheduleWrapper
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ScheduleApi : _BaseApi {
    @GET(SCHEDULES_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueScheduleWrapper>

    @GET(SCHEDULES_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<HueSchedule>

    @POST(SCHEDULES_ALL)
    fun createScedule(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Body hueSchedule: HueSchedule
    ): Call<SimpleResponse>

    @PUT(SCHEDULES_SINGLE)
    fun update(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueSchedule: HueSchedule
    ): Call<List<SimpleResponse>>

    @DELETE(SCHEDULES_SINGLE)
    fun delete(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<List<SimpleResponse>>

    companion object {
        private const val SCHEDULES_ALL = "$BASE_PATH/schedules"
        private const val SCHEDULES_SINGLE = "$SCHEDULES_ALL/{$ID_REPLACEMENT}"
    }
}