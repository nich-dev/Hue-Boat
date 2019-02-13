package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueSchedule
import com.nicholasholley.dev.hueboatsdk.data.models.HueScheduleWrapper
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ScheduleApi {
    @GET(ApiPaths.Schedule.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueScheduleWrapper>

    @GET(ApiPaths.Schedule.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<HueSchedule>

    @POST(ApiPaths.Schedule.ALL)
    fun createScedule(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueSchedule: HueSchedule
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Schedule.SINGLE)
    fun update(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueSchedule: HueSchedule
    ): Call<List<SimpleResponse>>

    @DELETE(ApiPaths.Schedule.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<List<SimpleResponse>>
}