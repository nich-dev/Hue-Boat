package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface SensorApi {

    @GET(ApiPaths.Sensor.SENSORS_ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueSensorWrapper>

    @GET(ApiPaths.Sensor.SENSORS_NEW)
    fun getNew(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueSensorWrapper>

    @GET(ApiPaths.Sensor.SENSORS_SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueSensor>

    @POST(ApiPaths.Sensor.SENSORS_ALL)
    fun findNew(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<List<SimpleResponse>>

    @POST(ApiPaths.Groups.ALL)
    fun post(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueSensor: HueSensor
    ): Deferred<SuccessWrapper>

    @PUT(ApiPaths.Sensor.SENSORS_SINGLE)
    fun put(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueSensor: HueSensor
    ): Deferred<List<SimpleResponse>>

    @PUT(ApiPaths.Sensor.SENSORS_CONFIG)
    fun putConfig(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueSensorConfig: HueSensorConfig
    ): Deferred<List<SimpleResponse>>

    @DELETE(ApiPaths.Sensor.SENSORS_SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<List<SimpleResponse>>
}
