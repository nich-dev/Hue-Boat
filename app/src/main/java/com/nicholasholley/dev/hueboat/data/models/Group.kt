package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.StateConverter
import com.nicholasholley.dev.hueboatsdk.data.models.HueState

@Entity
@TypeConverters(StateConverter::class)
data class Group(
        var id:         Long = 0L,
        var name:       String? = null,
        var type:       String? = null,
        var category:   String? = null,
        var modelid:    String? = null,
        var uniqueid:   String? = null,
        var action:     HueState? = null,
        var lightIds:   List<Long> = listOf()
)

abstract class GroupDao : BaseDao<Group>
