package com.example.locationreceiverkotlin.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleTagStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleTagStrategy::class)
interface LoginView : MvpView {
    fun showLoginSuccess()
    fun showLoginError()
    fun showValidateError()
    fun requestLocationPermission()
}