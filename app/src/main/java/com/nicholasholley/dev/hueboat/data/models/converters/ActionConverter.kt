package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.data.models.HueAction

class ActionConverter {
    @TypeConverter
    fun fromString(value: String?): HueAction? =
            value?.let {
                Boat.gson.fromJson(it, HueAction::class.java)
            }

    @TypeConverter
    fun toObject(list: HueAction?): String? =
            list?.let {
                Boat.gson.toJson(it)
            }
}
