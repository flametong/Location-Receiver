package com.example.locationreceiverkotlin.mvp.presenter

import com.example.locationreceiverkotlin.mvp.view.LoginView
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter(private val mAuth: FirebaseAuth) : MvpPresenter<LoginView>() {

    fun signIn(email: String, password: String) {
        if (email.isEmpty()
            || password.isEmpty()
        ) {
            viewState.showValidateError()
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        viewState.showLoginSuccess()
                    } else {
                        viewState.showLoginError()
                    }
                }
        }
    }
}