package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.network.api.SensorApi
import kotlinx.coroutines.Deferred

interface SensorRepo {
    fun create(hueSensor: HueSensor): Deferred<SuccessWrapper>
    fun getAll(): Deferred<HueSensorWrapper>
    fun get(id: String): Deferred<HueSensor>
    fun getNew(): Deferred<HueSensorWrapper>
    fun startSearch(): Deferred<List<SimpleResponse>>
    fun update(id: String, hueSensor: HueSensor): Deferred<List<SimpleResponse>>
    fun updateConfig(id: String, hueSensorConfig: HueSensorConfig): Deferred<List<SimpleResponse>>
    fun delete(id: String): Deferred<List<SimpleResponse>>
}

class RepoAccess(
        private val api: SensorApi,
        private val username: String
) : SensorRepo {
    override fun create(hueSensor: HueSensor) = api.post(username, hueSensor)

    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun update(id: String, hueSensor: HueSensor) = api.put(username, id, hueSensor)

    override fun delete(id: String) = api.delete(username, id)

    override fun getNew() = api.getNew(username)

    override fun startSearch() = api.findNew(username)

    override fun updateConfig(id: String, hueSensorConfig: HueSensorConfig) =
            api.putConfig(username, id, hueSensorConfig)
}
