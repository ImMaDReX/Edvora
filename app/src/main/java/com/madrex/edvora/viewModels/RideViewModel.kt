package com.madrex.edvora.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madrex.edvora.model.Ride
import com.madrex.edvora.model.User
import com.madrex.edvora.roomDb.RideDB
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class RideViewModel @Inject constructor(private val rideDB: RideDB): ViewModel() {

    private val ridesMLD = MutableLiveData<List<Ride>>()
    private val filteredRidesMLD = MutableLiveData<List<Ride>>()
    val ridesLD : LiveData<List<Ride>>
        get() = ridesMLD
    val filteredLD : LiveData<List<Ride>>
        get() = filteredRidesMLD
    init {
        viewModelScope.launch {
            val tmp:List<Ride> = rideDB.getRideDao().getRides()
            ridesMLD.postValue(tmp)
        }
    }
    fun getNearest(user: User){
        var list = mutableListOf<Ride>()
        for(ride in ridesLD.value!!){
            ride.distance = getShortestDistance(ride.station_path,user)
            list.add(ride)
        }
        list.sortBy { it.distance }
        filteredRidesMLD.postValue(list)
    }

    private fun getShortestDistance(paths:List<Int>, user: User): Int{
        var distance = Math.abs(paths[0]-user.station_code)
        for(i in 1 until paths.size){
            if(Math.abs(paths[i]-user.station_code)<distance)
                distance = Math.abs(paths[i]-user.station_code)
            if(paths[i]>user.station_code)
                break
        }
        return distance
    }

    fun getUpcoming(){
        val tmp = ridesLD.value?.filter {
            SimpleDateFormat("dd/mm/yyyy hh:mm a")?.parse(it.date).after(Date())
        }
        filteredRidesMLD.postValue(tmp)
    }

    fun getPast(){
        val tmp = ridesLD.value?.filter {
            SimpleDateFormat("dd/mm/yyyy hh:mm a")?.parse(it.date).before(Date())
        }
        filteredRidesMLD.postValue(tmp)
    }

}