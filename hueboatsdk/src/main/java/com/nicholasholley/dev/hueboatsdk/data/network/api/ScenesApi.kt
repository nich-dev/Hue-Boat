package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueScene
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.models.wrapper.HueSceneWrapper
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ScenesApi {
    @GET(SCENES_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueSceneWrapper>

    @GET(SCENES_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: String = ""
    ): Call<HueScene>

    @POST(SCENES_ALL)
    fun create(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Body hueScene: HueScene
    ): Call<SimpleResponse>

    @PUT(SCENES_SINGLE)
    fun update(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: String = "",
            @Body hueScene: HueScene
    ): Call<SimpleResponse>

    @PUT(SCENES_STATE)
    fun updateState(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: String = "",
            @Body hueState: HueState
    ): Call<SimpleResponse>

    @DELETE(SCENES_SINGLE)
    fun delete(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: String = ""
    )

    companion object {
        private const val SCENES_ALL = "$BASE_PATH/scenes"
        private const val SCENES_SINGLE = "$SCENES_ALL/{$ID_REPLACEMENT}"
        private const val SCENES_STATE = "$SCENES_SINGLE/lightstates/{$ID_REPLACEMENT}"
    }
}