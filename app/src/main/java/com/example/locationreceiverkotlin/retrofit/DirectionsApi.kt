package com.example.locationreceiverkotlin.retrofit

import com.example.locationreceiverkotlin.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApi {
    @GET(Constants.DIRECTIONS_API_LINK)
    fun getDirection(
        @Query(Constants.ORIGIN) origin: String?,
        @Query(Constants.DESTINATION) destination: String?,
        @Query(Constants.MODE) mode: String?,
        @Query(Constants.KEY) apiKey: String?
    ): Call<DirectionsResponses>
}