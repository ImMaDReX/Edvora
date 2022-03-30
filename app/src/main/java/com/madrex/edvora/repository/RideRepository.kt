package com.madrex.edvora.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.madrex.edvora.model.Ride
import com.madrex.edvora.model.User
import com.madrex.edvora.retrofitServices.RideApi
import com.madrex.edvora.roomDb.RideDB
import com.madrex.edvora.utils.Preferences
import javax.inject.Inject

class RideRepository @Inject constructor(private val rideApi: RideApi, private val rideDB: RideDB, private val preferences: Preferences){
    private val ridesMLD = MutableLiveData<List<Ride>>()
    private val userMLD = MutableLiveData<User>()
    val ridesLD : LiveData<List<Ride>>
        get() = ridesMLD
    val userLD : LiveData<User>
        get() = userMLD
    suspend fun getRides(){
        val result = rideApi.getRides()
        if(result.isSuccessful && result.body() != null){
            rideDB.getRideDao().clearTable()
            rideDB.getRideDao().addRides(result.body()!!)
            ridesMLD.postValue(result.body())
        } else {
            val tmp:List<Ride> = rideDB.getRideDao().getRides()
            ridesMLD.postValue(tmp)
        }
    }
    suspend fun getUser(){
        val result = rideApi.getUser()
        if(result.isSuccessful && result.body() != null){
            preferences.saveUser(result.body()!!)
            userMLD.postValue(result.body())
        } else {
            userMLD.postValue(preferences.getUser())
        }
    }
}