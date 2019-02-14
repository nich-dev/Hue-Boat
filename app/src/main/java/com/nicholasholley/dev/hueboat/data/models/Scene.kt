package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.LightStateWrapperConverter
import com.nicholasholley.dev.hueboat.data.models.converters.LongListConverter
import com.nicholasholley.dev.hueboatsdk.models.HueLightStateWrapper

@Entity
@TypeConverters(LightStateWrapperConverter::class, LongListConverter::class)
data class Scene(
    @PrimaryKey
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

@Dao
abstract class SceneDao : BaseDao<Scene>
