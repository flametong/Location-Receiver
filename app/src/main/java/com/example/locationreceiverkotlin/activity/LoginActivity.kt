package com.example.locationreceiverkotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.R
import com.example.locationreceiverkotlin.databinding.ActivityLoginBinding
import com.example.locationreceiverkotlin.mvp.presenter.LoginPresenter
import com.example.locationreceiverkotlin.mvp.view.LoginView
import com.google.firebase.auth.FirebaseAuth
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class LoginActivity : MvpAppCompatActivity(), LoginView {

    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var mAuth: FirebaseAuth

    @InjectPresenter
    lateinit var mPresenter: LoginPresenter

    @ProvidePresenter
    fun provideLoginPresenter(): LoginPresenter {
        return LoginPresenter(mAuth)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(root)

            btnLogin.setOnClickListener {
                val email = edEmail.text.toString().trim()
                val password = edPassword.text.toString().trim()
                mPresenter.signIn(email, password)
            }
        }
    }

    // Show successful login toast
    override fun showLoginSuccess() {
        Toast.makeText(
            this, R.string.login_successful,
            Toast.LENGTH_SHORT
        ).show()
        startActivity(Intent(baseContext, MenuActivity::class.java))
    }

    // Show failed login toast
    override fun showLoginError() {
        Toast.makeText(
            this, R.string.login_failed,
            Toast.LENGTH_SHORT
        ).show()
    }

    // Show error if text fields are empty
    override fun showValidateError() {
        Toast.makeText(
            this, R.string.validate_error,
            Toast.LENGTH_SHORT
        ).show()
    }
}