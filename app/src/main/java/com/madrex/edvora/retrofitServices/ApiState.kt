package com.madrex.edvora.retrofitServices

import com.madrex.edvora.model.Ride

sealed class ApiState {
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Ride>) : ApiState()
    object Empty : ApiState()
}