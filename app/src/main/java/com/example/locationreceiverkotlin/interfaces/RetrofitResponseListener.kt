package com.example.locationreceiverkotlin.interfaces

import com.example.locationreceiverkotlin.retrofit.DirectionsResponses
import retrofit2.Response

interface RetrofitResponseListener {
    fun putResponse(response: Response<DirectionsResponses?>?)
    fun putError(t: Throwable)
}