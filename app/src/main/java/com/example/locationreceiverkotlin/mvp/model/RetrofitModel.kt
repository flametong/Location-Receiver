package com.example.locationreceiverkotlin.mvp.model

import android.graphics.Color
import android.util.Log
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.interfaces.RetrofitResponseListener
import com.example.locationreceiverkotlin.retrofit.DirectionsApi
import com.example.locationreceiverkotlin.retrofit.DirectionsResponses
import com.example.locationreceiverkotlin.util.Constants
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.android.PolyUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitModel(private val mResponseListener: RetrofitResponseListener) {

    companion object {
        private var TAG = RetrofitModel::class.simpleName
    }

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
                response: Response<DirectionsResponses>
            ) {
                Log.d(TAG, "response: ${response.message()}")

                if (response.body() != null) {

                    val shape = response.body()?.getRoutes()?.get(0)
                        ?.getOverviewPolyline()?.getPoints()

                    val polyline = PolylineOptions()
                        .addAll(PolyUtil.decode(shape))
                        .width(Constants.POLYLINE_WIDTH)
                        .color(Color.RED)

                    mResponseListener.putPolyline(polylineOptions = polyline)
                }
            }

            override fun onFailure(call: Call<DirectionsResponses>, t: Throwable) {
                Log.d(TAG, "error: ${t.localizedMessage}")
            }
        })
    }
}