package com.madrex.edvora.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {

//    @TypeConverter
//    fun fromDateToLong(date: String): Long {
//        val formatter = SimpleDateFormat("dd/mm/yyyy hh:mm a")
//        return formatter.parse(date)?.time?:0
////        return date.time
//    }
//
//    @TypeConverter
//    fun fromLongToDate(value : Long) : Date {
//        return Date(value)
//    }


    @TypeConverter
    fun fromListToString(stationPath: List<Int>): String {
        return stationPath.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToList(stationPath: String): List<Int> {
        return stationPath.split(",").map { it.trim().toInt()}
    }
}