package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.LongListConverter
import com.nicholasholley.dev.hueboat.data.models.converters.StateConverter
import com.nicholasholley.dev.hueboatsdk.models.HueState

@Entity
@TypeConverters(StateConverter::class, LongListConverter::class)
data class Group(
        @PrimaryKey
        var id:         String,
        var name:       String? = null,
        var type:       String? = null,
        var category:   String? = null,
        var modelid:    String? = null,
        var uniqueid:   String? = null,
        var action:     HueState? = null,
        var lightIds:   List<Long> = listOf()
)

@Dao
abstract class GroupDao : BaseDao<Group>
