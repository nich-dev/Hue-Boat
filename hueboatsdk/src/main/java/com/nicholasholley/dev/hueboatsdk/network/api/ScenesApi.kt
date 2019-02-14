package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.HueScene
import com.nicholasholley.dev.hueboatsdk.models.HueSceneWrapper
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import com.nicholasholley.dev.hueboatsdk.models.SuccessWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface ScenesApi {
    @GET(ApiPaths.Scene.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueSceneWrapper>

    @GET(ApiPaths.Scene.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueScene>

    @POST(ApiPaths.Scene.ALL)
    fun post(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueScene: HueScene
    ): Deferred<SuccessWrapper>

    @PUT(ApiPaths.Scene.SINGLE)
    fun put(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueScene: HueScene
    ): Deferred<List<SimpleResponse>>

    @DELETE(ApiPaths.Scene.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<List<SimpleResponse>>
}
