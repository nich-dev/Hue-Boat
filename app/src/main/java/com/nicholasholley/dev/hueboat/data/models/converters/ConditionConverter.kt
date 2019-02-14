package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nicholasholley.dev.hueboatsdk.models.HueCondition

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

class ConditionListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<HueCondition>? =
            value?.let {
                gson.fromJson(it, listOf<HueCondition>()::class.java)
            }

    @TypeConverter
    fun toObject(list: List<HueCondition>?): String? =
            list?.let {
                gson.toJson(it)
            }
}
