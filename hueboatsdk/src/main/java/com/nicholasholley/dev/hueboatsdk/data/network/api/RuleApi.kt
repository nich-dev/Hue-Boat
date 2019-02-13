package com.nicholasholley.dev.hueboatsdk.data.network.api

import com.nicholasholley.dev.hueboatsdk.data.models.HueRule
import com.nicholasholley.dev.hueboatsdk.data.models.HueRuleWrapper
import com.nicholasholley.dev.hueboatsdk.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface RuleApi {

    @GET(ApiPaths.Rules.ALL)
    fun getAll(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?
    ): Call<HueRuleWrapper>

    @GET(ApiPaths.Rules.SINGLE)
    fun get(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<HueRule>

    @POST(ApiPaths.Rules.ALL)
    fun create(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueRule: HueRule
    ): Call<List<SimpleResponse>>

    @PUT(ApiPaths.Rules.ALL)
    fun update(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Body hueRule: HueRule
    ): Call<List<SimpleResponse>>

    @DELETE(ApiPaths.Rules.SINGLE)
    fun delete(
            @Path(value = ApiPaths.Base.USERNAME_REPLACEMENT) username: String?,
            @Path(value = ApiPaths.Base.ID_REPLACEMENT) id: Long = 0L
    ): Call<List<SimpleResponse>>
}
