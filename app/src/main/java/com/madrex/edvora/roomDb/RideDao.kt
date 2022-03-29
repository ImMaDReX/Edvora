package com.madrex.edvora.roomDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madrex.edvora.model.Ride

@Dao
interface RideDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRides(rides: List<Ride>)

    @Query("SELECT * FROM Ride")
    suspend fun getRides(): List<Ride>

    @Query("DELETE FROM Ride")
    suspend fun clearTable()
}