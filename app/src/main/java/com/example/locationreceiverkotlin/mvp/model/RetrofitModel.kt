package com.example.locationreceiverkotlin.mvp.model

import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.interfaces.RetrofitResponseListener
import com.example.locationreceiverkotlin.retrofit.DirectionsApi
import com.example.locationreceiverkotlin.retrofit.DirectionsResponses
import com.example.locationreceiverkotlin.util.Constants
import com.example.locationreceiverkotlin.util.LoggerImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitModel(private val mResponseListener: RetrofitResponseListener) {

    companion object {
        private var TAG = RetrofitModel::class.simpleName
    }

    private val logger = LoggerImpl()

    @Inject
    lateinit var mDirectionsApi: DirectionsApi

    init {
        App.appComponent.inject(this)
    }

    fun sendRequest(fromOrigin: String, toDestination: String) {
        mDirectionsApi.getDirection(
            fromOrigin,
            toDestination,
            Constants.MODE_WALKING,
            Constants.DIRECTIONS_API_KEY
        ).enqueue(object : Callback<DirectionsResponses> {
            override fun onResponse(
                call: Call<DirectionsResponses>,
                response: Response<DirectionsResponses?>?
            ) {
                logger.logd(TAG, "response: ${response?.message()}")
                mResponseListener.putResponse(response)
            }

            override fun onFailure(call: Call<DirectionsResponses>, t: Throwable) {
                logger.logd(TAG, "error: ${t.localizedMessage}")
                mResponseListener.putError(t)
            }
        })
    }
}