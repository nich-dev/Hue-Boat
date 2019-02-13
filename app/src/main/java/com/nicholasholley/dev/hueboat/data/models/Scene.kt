package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.LightStateWrapperConverter
import com.nicholasholley.dev.hueboatsdk.data.models.HueLightStateWrapper

@Entity
@TypeConverters(LightStateWrapperConverter::class)
data class Scene(
    var id:             String,
    var name:           String? = null,
    var type:           String? = null,
    var owner:          String? = null,
    var picture:        String? = null,
    var lastupdated:    String? = null,
    var recycle:        Boolean? = false,
    var locked:         Boolean? = null,
    var version:        Int? = null,
    var lightIds:       List<Long> = listOf(),
    var lightstates:    HueLightStateWrapper? = null
)

abstract class SceneDao : BaseDao<Scene>
