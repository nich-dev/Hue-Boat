package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.models.HueLight
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LightsApi {

    @GET(LIGHTS_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<List<HueLight>>

    @GET(LIGHTS_NEW)
    fun getNew(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    )

    @GET(LIGHTS_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = USERNAME_REPLACEMENT) id: Long = 0L
    )

    @POST(LIGHTS_ALL)
    fun searchNew(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    )

    companion object {
        private const val USERNAME_REPLACEMENT = "<username>"
        private const val ID_REPLACEMENT = "<id>"

        private const val LIGHTS_ALL = "/api/$USERNAME_REPLACEMENT/lights"
        private const val LIGHTS_NEW = "$LIGHTS_ALL/new"
        private const val LIGHTS_SINGLE = "$LIGHTS_ALL/$ID_REPLACEMENT"
    }
}