package com.example.locationreceiverkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.R
import com.example.locationreceiverkotlin.databinding.ActivityMenuBinding
import com.example.locationreceiverkotlin.mvp.presenter.MenuPresenter
import com.example.locationreceiverkotlin.mvp.view.MenuView
import com.example.locationreceiverkotlin.util.Variables
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MenuActivity : MvpAppCompatActivity(), MenuView {

    lateinit var binding: ActivityMenuBinding

    @Inject
    lateinit var mAuth: FirebaseAuth

    @InjectPresenter
    lateinit var mPresenter: MenuPresenter

    @ProvidePresenter
    fun provideMenuPresenter(): MenuPresenter = MenuPresenter(mAuth)

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        Variables.isMapActive = false

        binding = ActivityMenuBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            btnShowCurrentLocation.setOnClickListener {
                mPresenter.showMap()
            }

            btnSignOut.setOnClickListener {
                mPresenter.signOut()
            }
        }
    }

    // Show sign out toast and go to LoginActivity
    override fun signOutUser() {
        Toast.makeText(
            this, R.string.sign_out_completed,
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(baseContext, LoginActivity::class.java))
    }

    // Go to MapsActivity
    override fun startMapsActivity() {
        if (Variables.isMapActive)
            startActivity(Intent(baseContext, MapsActivity::class.java))
    }
}