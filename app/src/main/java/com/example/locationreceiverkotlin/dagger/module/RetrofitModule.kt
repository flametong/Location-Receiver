package com.example.locationreceiverkotlin.dagger.module

import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.R
import com.example.locationreceiverkotlin.retrofit.DirectionsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    @Provides
    @Singleton
    fun provideDirectionsApi(): DirectionsApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(App.context.getString(R.string.base_url))
        .build()
        .create(DirectionsApi::class.java)

}