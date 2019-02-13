package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.CommandConverter
import com.nicholasholley.dev.hueboatsdk.data.models.HueCommand

@Entity
@TypeConverters(CommandConverter::class)
data class Schedule(
    var id:             Long = 0L,
    var name:           String? = null,
    var description:    String? = null,
    var localtime:      String? = null,
    var status:         String? = null,
    var autodelete:     Boolean = true,
    var recycle:        Boolean = false,
    var command:        HueCommand? = null
)

abstract class ScheduleDao : BaseDao<Schedule>

