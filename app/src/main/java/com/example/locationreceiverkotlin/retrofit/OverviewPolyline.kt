package com.example.locationreceiverkotlin.retrofit

import com.example.locationreceiverkotlin.util.Constants
import com.google.gson.annotations.SerializedName

class OverviewPolyline {

    @SerializedName(Constants.POINTS)
    private val points: String? = null

    fun getPoints(): String? {
        return points
    }
}