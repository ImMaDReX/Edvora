package com.madrex.edvora.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Ride(
    val city: String,
    val date: String,
    val destination_station_code: Int,
    @PrimaryKey
    val id: Int,
    val map_url: String,
    val origin_station_code: Int,
    val state: String,
    val station_path: List<Int>,
    var distance:Int
)