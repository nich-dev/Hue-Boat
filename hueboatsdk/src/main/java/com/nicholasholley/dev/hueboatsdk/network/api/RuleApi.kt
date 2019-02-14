package com.nicholasholley.dev.hueboatsdk.network.api

import com.nicholasholley.dev.hueboatsdk.models.HueRule
import com.nicholasholley.dev.hueboatsdk.models.HueRuleWrapper
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import com.nicholasholley.dev.hueboatsdk.models.SuccessWrapper
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface RuleApi {

    @GET(ApiPaths.Rules.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Deferred<HueRuleWrapper>

    @GET(ApiPaths.Rules.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<HueRule>

    @POST(ApiPaths.Rules.ALL)
    fun post(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueRule: HueRule
    ): Deferred<SuccessWrapper>

    @PUT(ApiPaths.Rules.SINGLE)
    fun put(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String,
            @Body hueRule: HueRule
    ): Deferred<List<SimpleResponse>>

    @DELETE(ApiPaths.Rules.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: String
    ): Deferred<List<SimpleResponse>>
}
