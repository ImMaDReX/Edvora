package com.madrex.edvora.roomDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.madrex.edvora.model.Ride
import com.madrex.edvora.utils.Converters

@Database(entities = [Ride::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RideDB : RoomDatabase(){
    abstract fun getRideDao():RideDao
}