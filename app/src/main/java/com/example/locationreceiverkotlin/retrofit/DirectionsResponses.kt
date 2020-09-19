package com.example.locationreceiverkotlin.retrofit

import com.example.locationreceiverkotlin.util.Constants
import com.google.gson.annotations.SerializedName

class DirectionsResponses {

    @SerializedName(Constants.ROUTES)
    private var routes: List<RoutesItem>? = null

    fun getRoutes(): List<RoutesItem?>? = routes
}