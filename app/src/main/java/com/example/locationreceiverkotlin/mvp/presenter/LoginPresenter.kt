package com.example.locationreceiverkotlin.mvp.presenter

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.locationreceiverkotlin.mvp.view.LoginView
import com.example.locationreceiverkotlin.util.Constants
import com.google.firebase.auth.FirebaseAuth
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter(private val mAuth: FirebaseAuth) : MvpPresenter<LoginView>() {

    companion object {
        private var TAG = LoginPresenter::class.simpleName
    }

    fun signIn(email: String, password: String) {
        if (TextUtils.isEmpty(email)
            || TextUtils.isEmpty(password)
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

    fun getLocationPermissions(context: Context) {
        if (context.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Constants.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission granted")
        } else {
            viewState.requestLocationPermission()
        }
    }
}