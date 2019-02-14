package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.models.HueLightStateWrapper

class LightStateWrapperConverter {
    @TypeConverter
    fun fromString(value: String?): HueLightStateWrapper? =
            value?.let {
                Boat.gson.fromJson(it, HueLightStateWrapper::class.java)
            }

    @TypeConverter
    fun toObject(list: HueLightStateWrapper?): String? =
            list?.let {
                Boat.gson.toJson(it)
            }
}
