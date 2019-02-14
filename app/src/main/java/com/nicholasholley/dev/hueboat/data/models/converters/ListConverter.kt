package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.google.gson.Gson

class LongListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<Long>? =
            value?.let {
                gson.fromJson(it, listOf<Long>()::class.java)
            }

    @TypeConverter
    fun toObject(list: List<Long>?): String? =
            list?.let {
                gson.toJson(it)
            }
}
