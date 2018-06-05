package com.nicholasholley.dev.hueboat.data.network.api

import com.nicholasholley.dev.hueboat.data.models.HueLight
import com.nicholasholley.dev.hueboat.data.models.HueRule
import com.nicholasholley.dev.hueboat.data.models.wrapper.HueRuleWrapper
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.BASE_PATH
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.ID_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.api._BaseApi.Companion.USERNAME_REPLACEMENT
import com.nicholasholley.dev.hueboat.data.network.json.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface RuleApi {

    @GET(RULES_ALL)
    fun getAll(
            @Path(value = USERNAME_REPLACEMENT) username: String?
    ): Call<HueRuleWrapper>

    @GET(RULES_SINGLE)
    fun get(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<HueRule>

    @POST(RULES_ALL)
    fun create(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Body hueRule: HueRule
    ): Call<List<SimpleResponse>>

    @PUT(RULES_ALL)
    fun update(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Body hueRule: HueRule
    ): Call<List<SimpleResponse>>

    @DELETE(RULES_SINGLE)
    fun delete(
            @Path(value = USERNAME_REPLACEMENT) username: String?,
            @Path(value = ID_REPLACEMENT) id: Long = 0L
    ): Call<List<SimpleResponse>>

    companion object {
        private const val RULES_ALL = "$BASE_PATH/rules"
        private const val RULES_SINGLE = "$RULES_ALL/{$ID_REPLACEMENT}"
    }
}