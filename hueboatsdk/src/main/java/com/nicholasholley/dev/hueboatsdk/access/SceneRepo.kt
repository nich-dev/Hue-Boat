package com.nicholasholley.dev.hueboatsdk.access

import com.nicholasholley.dev.hueboatsdk.models.HueScene
import com.nicholasholley.dev.hueboatsdk.models.HueSceneWrapper
import com.nicholasholley.dev.hueboatsdk.models.SimpleResponse
import com.nicholasholley.dev.hueboatsdk.models.SuccessWrapper
import com.nicholasholley.dev.hueboatsdk.network.api.ScenesApi
import kotlinx.coroutines.Deferred

interface SceneRepo {
    fun create(hueScene: HueScene): Deferred<SuccessWrapper>
    fun getAll(): Deferred<HueSceneWrapper>
    fun get(id: String): Deferred<HueScene>
    fun update(id: String, hueScene: HueScene): Deferred<List<SimpleResponse>>
    fun delete(id: String): Deferred<List<SimpleResponse>>
}

class SceneAccess(
        private val api: ScenesApi,
        private val username: String
) : SceneRepo {
    override fun create(hueScene: HueScene) = api.post(username, hueScene)

    override fun getAll() = api.getAll(username)

    override fun get(id: String) = api.get(username, id)

    override fun update(id: String, hueScene: HueScene) = api.put(username, id, hueScene)

    override fun delete(id: String) = api.delete(username, id)
}
