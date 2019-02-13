package com.nicholasholley.dev.hueboat.data.models

import androidx.room.Entity
import androidx.room.TypeConverters
import com.nicholasholley.dev.hueboat.data.models.converters.StateConverter
import com.nicholasholley.dev.hueboatsdk.data.models.HueState

@Entity
@TypeConverters(StateConverter::class)
data class Light(
        var id:                 Long = 0,
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

abstract class LightDao : BaseDao<Light>