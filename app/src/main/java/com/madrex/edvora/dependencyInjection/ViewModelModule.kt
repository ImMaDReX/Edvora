package com.madrex.edvora.dependencyInjection

import androidx.lifecycle.ViewModel
import com.madrex.edvora.viewModels.RideViewModel
import com.madrex.edvora.viewModels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
abstract class ViewModelModule {
    @Binds
    @ClassKey(SplashViewModel::class)
    @IntoMap
    abstract fun getSplashViewModel(splashViewModel: SplashViewModel) : ViewModel

    @Binds
    @ClassKey(RideViewModel::class)
    @IntoMap
    abstract fun getRideViewModel(rideViewModel: RideViewModel) : ViewModel
}