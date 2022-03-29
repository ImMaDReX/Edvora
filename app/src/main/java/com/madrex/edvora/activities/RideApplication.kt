package com.madrex.edvora.activities

import android.app.Application
import android.content.SharedPreferences
import com.madrex.edvora.dependencyInjection.ApplicationComponent
import com.madrex.edvora.dependencyInjection.DaggerApplicationComponent

class RideApplication : Application() {

    lateinit var appComponent:ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(applicationContext)
    }
}