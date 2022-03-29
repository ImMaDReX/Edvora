package com.madrex.edvora.dependencyInjection

import com.madrex.edvora.retrofitServices.RideApi
import com.madrex.edvora.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getRideApi(retrofit: Retrofit):RideApi{
        return retrofit.create(RideApi::class.java)
    }
}