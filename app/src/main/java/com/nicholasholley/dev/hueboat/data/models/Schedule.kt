package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.CommandConverter
import com.nicholasholley.dev.hueboatsdk.models.HueCommand

@Entity
@TypeConverters(CommandConverter::class)
data class Schedule(
    @PrimaryKey
    var id:             String,
    var name:           String? = null,
    var description:    String? = null,
    var localtime:      String? = null,
    var status:         String? = null,
    var autodelete:     Boolean = true,
    var recycle:        Boolean = false,
    var command:        HueCommand? = null
)

@Dao
abstract class ScheduleDao : BaseDao<Schedule>
