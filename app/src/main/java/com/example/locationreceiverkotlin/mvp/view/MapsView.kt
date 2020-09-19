package com.example.locationreceiverkotlin.mvp.view

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MapsView : MvpView {
    fun clearMap()
    fun addMarkerToMap(marker: MarkerOptions)
    fun moveMapCamera(coordinates: LatLng)
    fun drawLine(polylineOptions: PolylineOptions)
}