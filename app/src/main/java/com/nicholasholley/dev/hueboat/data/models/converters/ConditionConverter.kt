package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nicholasholley.dev.hueboatsdk.data.models.HueCondition

class ConditionConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): HueCondition? =
            value?.let {
                gson.fromJson(it, HueCondition::class.java)
            }

    @TypeConverter
    fun toObject(list: HueCondition?): String? =
            list?.let {
                gson.toJson(it)
            }
}
