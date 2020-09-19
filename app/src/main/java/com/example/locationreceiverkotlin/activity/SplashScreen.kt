package com.example.locationreceiverkotlin.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.locationreceiverkotlin.App
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SplashScreen : AppCompatActivity() {

    @Inject
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (mAuth.currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else {
            startActivity(Intent(this, MenuActivity::class.java))
        }
        finish()
    }
}