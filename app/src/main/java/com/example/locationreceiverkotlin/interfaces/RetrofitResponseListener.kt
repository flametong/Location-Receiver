package com.example.locationreceiverkotlin.interfaces

import com.google.android.gms.maps.model.PolylineOptions

interface RetrofitResponseListener {
    fun putPolyline(polylineOptions: PolylineOptions)
}