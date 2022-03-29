package com.madrex.edvora.dependencyInjection

import android.content.Context
import androidx.room.Room
import com.madrex.edvora.roomDb.RideDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getRoomDatabase(context: Context) : RideDB{
        return Room.databaseBuilder(context,RideDB::class.java,"RideDB").build()
    }
}