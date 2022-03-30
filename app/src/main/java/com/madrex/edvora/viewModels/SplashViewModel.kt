package com.madrex.edvora.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrex.edvora.model.Ride
import com.madrex.edvora.model.User
import com.madrex.edvora.repository.RideRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val rideRepository: RideRepository) : ViewModel() {
    val ridesLD : LiveData<List<Ride>>
        get() = rideRepository.ridesLD
    val userLD : LiveData<User>
        get() = rideRepository.userLD
    init {
        viewModelScope.launch {
            rideRepository.getRides()
            rideRepository.getUser()
        }
    }
}