package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueScene
import com.nicholasholley.dev.hueboatsdk.data.models.HueSceneWrapper
import com.nicholasholley.dev.hueboatsdk.data.models.HueState
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface ScenesApi {
    @GET(ApiPaths.Scene.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueSceneWrapper>

    @GET(ApiPaths.Scene.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String = ""
    ): Call<HueScene>

    @POST(ApiPaths.Scene.ALL)
    fun create(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueScene: HueScene
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Scene.SINGLE)
    fun update(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String = "",
            @Body hueScene: HueScene
    ): Call<SimpleResponse>

    @PUT(ApiPaths.Scene.STATE)
    fun updateState(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String = "",
            @Body hueState: HueState
    ): Call<SimpleResponse>

    @DELETE(ApiPaths.Scene.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String = ""
    )
}
