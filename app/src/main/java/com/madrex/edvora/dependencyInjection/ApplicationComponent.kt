package com.madrex.edvora.dependencyInjection

import android.content.Context
import androidx.lifecycle.ViewModel
import com.madrex.edvora.activities.RideApplication
import com.madrex.edvora.activities.RideScreen
import com.madrex.edvora.activities.SplashScreen
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class,ViewModelModule::class,AppModule::class])
interface ApplicationComponent {

    fun getMap() : Map<Class<*>, ViewModel>

    fun inject(splashScreen: SplashScreen)

    fun inject(rideScreen: RideScreen)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):ApplicationComponent
    }
}