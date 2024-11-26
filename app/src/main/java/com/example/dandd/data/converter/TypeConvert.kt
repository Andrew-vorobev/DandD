package com.example.dandd.data.converter

import androidx.room.TypeConverter

/**
 * @author Andrew
 */
class TypeConvert {
    @TypeConverter
    fun convertListStringToString(list: List<String>?) : String? {
        return list?.joinToString(separator = SEPARATOR_KEY) { it }
    }

    @TypeConverter
    fun convertStringToListString(string: String?): List<String>? {
        return string?.split(SEPARATOR_KEY)
    }

    companion object{
        private const val SEPARATOR_KEY = ", "
    }
}