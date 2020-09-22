package com.example.locationreceiverkotlin.mvp.presenter

import com.example.locationreceiverkotlin.mvp.view.MenuView
import com.example.locationreceiverkotlin.util.Variables
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MenuPresenter(private val mAuth: FirebaseAuth) : MvpPresenter<MenuView>() {

    fun showMap() {
        Variables.isMapActive = true
        viewState.startMapsActivity()
    }

    fun signOut() {
        mAuth.signOut()
        viewState.signOutUser()
    }
}