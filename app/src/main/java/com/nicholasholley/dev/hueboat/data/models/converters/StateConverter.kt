package com.nicholasholley.dev.hueboat.data.models.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nicholasholley.dev.hueboatsdk.data.models.HueState

class StateConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): HueState? =
            value?.let {
                gson.fromJson(it, HueState::class.java)
            }

    @TypeConverter
    fun toObject(list: HueState?): String? =
            list?.let {
                gson.toJson(it)
            }
}
