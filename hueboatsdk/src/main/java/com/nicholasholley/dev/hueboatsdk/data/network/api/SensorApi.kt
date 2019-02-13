package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueSensor
import com.nicholasholley.dev.hueboatsdk.data.models.HueSensorConfig
import com.nicholasholley.dev.hueboatsdk.data.models.HueSensorWrapper
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface SensorApi {

    @GET(ApiPaths.Sensor.SENSORS_ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueSensorWrapper>

    @GET(ApiPaths.Sensor.SENSORS_NEW)
    fun getNew(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueSensorWrapper>

    @GET(ApiPaths.Sensor.SENSORS_SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<HueSensor>

    @POST(ApiPaths.Sensor.SENSORS_ALL)
    fun startNewSearch(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<List<SimpleResponse>>

    @PUT(ApiPaths.Sensor.SENSORS_SINGLE)
    fun update(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueSensor: HueSensor
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Sensor.SENSORS_CONFIG)
    fun updateConfig(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueSensorConfig: HueSensorConfig
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Sensor.SENSORS_STATE)
    fun updateState(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L,
            @Body hueState: HueState
    ): Call<SimpleResponse>

    @DELETE(ApiPaths.Sensor.SENSORS_SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<SimpleResponse>
}
