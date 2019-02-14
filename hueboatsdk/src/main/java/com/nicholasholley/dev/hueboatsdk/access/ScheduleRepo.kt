package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.*
import com.nicholasholley.dev.hueboatsdk.network.api.ScheduleApi
import kotlinx.coroutines.Deferred

interface ScheduleRepo {
    fun create(hueSchedule: HueSchedule): Deferred<SuccessWrapper>
    fun getAll(): Deferred<HueScheduleWrapper>
    fun get(id: String): Deferred<HueSchedule>
    fun update(id: String, hueSchedule: HueSchedule): Deferred<List<SimpleResponse>>
    fun delete(id: String): Deferred<List<SimpleResponse>>
}

class ScheduleAccess(
        private val api: ScheduleApi,
        private val username: String
) : ScheduleRepo {
    override fun create(hueSchedule: HueSchedule) = api.post(username, hueSchedule)

    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun update(id: String, hueSchedule: HueSchedule) = api.put(username, id, hueSchedule)

    override fun delete(id: String) = api.delete(username, id)
}
