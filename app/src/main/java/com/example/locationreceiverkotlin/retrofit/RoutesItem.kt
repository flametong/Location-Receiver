package com.example.locationreceiverkotlin.retrofit

import com.example.locationreceiverkotlin.util.Constants
import com.google.gson.annotations.SerializedName

class RoutesItem {

    @SerializedName(Constants.OVERVIEW_POLYLINE)
    private var overviewPolyline: OverviewPolyline? = null

    fun getOverviewPolyline(): OverviewPolyline? = overviewPolyline
}