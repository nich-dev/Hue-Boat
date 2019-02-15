package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.network.api.LightsApi
import kotlinx.coroutines.Deferred

interface LightRepo {
    fun getAll(): Deferred<HueLightWrapper>
    fun get(id: String): Deferred<HueLight>
    fun getNew(): Deferred<HueLightWrapper>
    fun update(id: String, hueLight: HueLight): Deferred<List<SimpleResponse>>
    fun updateState(id: String, hueState: HueState): Deferred<List<SimpleResponse>>
    fun startSearch(): Deferred<List<SimpleResponse>>
}

class LightAccess(
        private val api: LightsApi,
        private val username: String
) : LightRepo {
    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun getNew() = api.getNew(username)

    override fun update(id: String, hueLight: HueLight) = api.put(username, id, hueLight)

    override fun updateState(id: String, hueState: HueState) = api.putState(username, id, hueState)

    override fun startSearch() = api.startNewSearch(username)
}
