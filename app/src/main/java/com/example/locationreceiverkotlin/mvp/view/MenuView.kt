package com.example.locationreceiverkotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MenuView : MvpView {
    fun showRequestLocationDialog()
    fun signOutUser()
    fun startMapsActivity()
}