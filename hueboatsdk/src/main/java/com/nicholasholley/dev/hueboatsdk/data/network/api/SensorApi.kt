package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueSensor
import com.nicholasholley.dev.hueboatsdk.data.models.HueSensorConfig
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueSensorWrapper
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface SensorApi {

    @GET(SENSORS_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueSensorWrapper>

    @GET(SENSORS_NEW)
    fun getNew(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueSensorWrapper>

    @GET(SENSORS_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<HueSensor>

    @POST(SENSORS_ALL)
    fun startNewSearch(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<List<SimpleResponse>>

    @PUT(SENSORS_SINGLE)
    fun update(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueSensor: HueSensor
    ): Call<SimpleResponse>

    @PUT(SENSORS_CONFIG)
    fun updateConfig(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueSensorConfig: HueSensorConfig
    ): Call<SimpleResponse>

    @PUT(SENSORS_STATE)
    fun updateState(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L,
            @Body hueState: HueState
    ): Call<SimpleResponse>

    @DELETE(SENSORS_SINGLE)
    fun delete(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<SimpleResponse>

    companion object {
        private const val SENSORS_ALL = "$BASE_PATH/sensors"
        private const val SENSORS_NEW = "$BASE_PATH/sensors/new"
        private const val SENSORS_SINGLE = "$BASE_PATH/sensors/{$ID_REPLACEMENT}"
        private const val SENSORS_CONFIG = "$SENSORS_SINGLE/config"
        private const val SENSORS_STATE = "$SENSORS_SINGLE/state"
    }
}