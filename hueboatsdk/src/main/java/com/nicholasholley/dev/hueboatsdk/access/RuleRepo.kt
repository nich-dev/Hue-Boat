package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.network.api.RuleApi
import kotlinx.coroutines.Deferred

interface RuleRepo {
    fun create(hueRule: HueRule): Deferred<SuccessWrapper>
    fun getAll(): Deferred<HueRuleWrapper>
    fun get(id: String): Deferred<HueRule>
    fun update(id: String, hueRule: HueRule): Deferred<List<SimpleResponse>>
    fun delete(id: String): Deferred<List<SimpleResponse>>
}

class RuleAccess(
        private val api: RuleApi,
        private val username: String
) : RuleRepo {
    override fun create(hueRule: HueRule) = api.post(username, hueRule)

    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun update(id: String, hueRule: HueRule) = api.put(username, id, hueRule)

    override fun delete(id: String) = api.delete(username, id)
}
