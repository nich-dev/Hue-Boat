package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.nicholasholley.dev.hueboatsdk.Boat
import com.nicholasholley.dev.hueboatsdk.models.HueAction

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

class ActionListConverter {
    @TypeConverter
    fun fromString(value: String?): List<HueAction>? =
            value?.let {
                Boat.gson.fromJson(it, listOf<HueAction>()::class.java)
            }

    @TypeConverter
    fun toObject(list: List<HueAction>?): String? =
            list?.let {
                Boat.gson.toJson(it)
            }
}
