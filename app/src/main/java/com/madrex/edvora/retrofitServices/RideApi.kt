package com.madrex.edvora.retrofitServices

import com.madrex.edvora.model.Ride
import com.madrex.edvora.model.User
import retrofit2.Response
import retrofit2.http.GET

interface RideApi {
    @GET("rides")
    suspend fun getRides() : Response<List<Ride>>

    @GET("user")
    suspend fun getUser() : Response<User>
}