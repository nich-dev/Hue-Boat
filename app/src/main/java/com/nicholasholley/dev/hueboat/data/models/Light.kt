package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.StateConverter
import com.nicholasholley.dev.hueboatsdk.models.HueState

@Entity
@TypeConverters(StateConverter::class)
data class Light(
        @PrimaryKey
        var id:                 String,
        var state:              HueState? = null,
        var type:               String? = null,
        var name:               String? = null,
        var modelid:            String? = null,
        var uniqueid:           String? = null,
        var manufacturername:   String? = null,
        var productname:        String? = null,
        var swversion:          String? = null,
        var luminaireuniqueid:  String? = null
)

@Dao
abstract class LightDao : BaseDao<Light>