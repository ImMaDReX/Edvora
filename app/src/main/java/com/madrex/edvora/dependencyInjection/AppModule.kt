package com.madrex.edvora.dependencyInjection

import android.content.Context
import android.content.SharedPreferences
import com.madrex.edvora.utils.Constants.RIDE_PREFERENCE
import com.madrex.edvora.utils.Preferences
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(RIDE_PREFERENCE, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun getPreference(sharedPreferences: SharedPreferences):Preferences{
        return Preferences(sharedPreferences)
    }
}