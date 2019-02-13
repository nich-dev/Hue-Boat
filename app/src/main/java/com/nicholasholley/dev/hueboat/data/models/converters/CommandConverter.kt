package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.data.models.HueCommand

class CommandConverter {
    @TypeConverter
    fun fromString(value: String?): HueCommand? =
            value?.let {
                Boat.gson.fromJson(it, HueCommand::class.java)
            }

    @TypeConverter
    fun toObject(list: HueCommand?): String? =
            list?.let {
                Boat.gson.toJson(it)
            }
}
