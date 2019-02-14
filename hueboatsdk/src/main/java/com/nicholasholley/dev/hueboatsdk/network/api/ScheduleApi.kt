package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.HueSchedule
import com.nicholasholley.dev.hueboatsdk.models.HueScheduleWrapper
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import com.nicholasholley.dev.hueboatsdk.models.SuccessWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ScheduleApi {
    @GET(ApiPaths.Schedule.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueScheduleWrapper>

    @GET(ApiPaths.Schedule.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueSchedule>

    @POST(ApiPaths.Schedule.ALL)
    fun post(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueSchedule: HueSchedule
    ): Deferred<SuccessWrapper>

    @PUT(ApiPaths.Schedule.SINGLE)
    fun put(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueSchedule: HueSchedule
    ): Deferred<List<SimpleResponse>>

    @DELETE(ApiPaths.Schedule.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<List<SimpleResponse>>
}