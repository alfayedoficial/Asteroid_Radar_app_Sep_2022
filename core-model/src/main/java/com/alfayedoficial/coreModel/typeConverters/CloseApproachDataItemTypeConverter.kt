package com.alfayedoficial.coreModel.typeConverters

import androidx.room.TypeConverter
import com.alfayedoficial.coreModel.apiResponse.asteroidResponses.CloseApproachDataItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class CloseApproachDataItemTypeConverter {
    @TypeConverter
    fun fromListToString(list: ArrayList<CloseApproachDataItem?>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<CloseApproachDataItem?>?>() {}.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromStringToList(string: String?): ArrayList<CloseApproachDataItem?>? {
        if (string == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<CloseApproachDataItem?>?>() {}.type
        return gson.fromJson<ArrayList<CloseApproachDataItem?>>(string, type)
    }
}