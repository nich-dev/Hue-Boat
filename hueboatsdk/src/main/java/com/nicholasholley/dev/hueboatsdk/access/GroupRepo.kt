package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.network.api.GroupsApi
import kotlinx.coroutines.Deferred

interface GroupRepo {
    fun create(hueGroup: HueGroup): Deferred<SuccessWrapper>
    fun getAll(): Deferred<HueGroupWrapper>
    fun get(id: String): Deferred<HueGroup>
    fun update(id: String, hueGroup: HueGroup): Deferred<List<SimpleResponse>>
    fun updateState(id: String, hueState: HueState): Deferred<List<SimpleResponse>>
    fun delete(id: String): Deferred<List<SimpleResponse>>
}

class GroupAccess(
        private val api: GroupsApi,
        private val username: String
) : GroupRepo {
    override fun create(hueGroup: HueGroup) = api.post(username, hueGroup)

    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun update(id: String, hueGroup: HueGroup) = api.put(username, id, hueGroup)

    override fun updateState(id: String, hueState: HueState) = api.putState(username, id, hueState)

    override fun delete(id: String) = api.delete(username, id)
}
