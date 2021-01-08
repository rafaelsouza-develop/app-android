package br.com.app4pets.app.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.app4pets.app.R
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.modules.home.HomeActivity
import br.com.app4pets.app.modules.register.RegisterActivity
import br.com.app4pets.app.data.network.models.LoginRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListners()
        setupObserverViewState(viewModel)
    }

    private fun setListners() {
        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (verifyFields()) {
                viewModel.login(LoginRequest(edtEmail.text.toString(), edtPass.text.toString()))
            }
        }
    }

    private fun setupObserverViewState(viewModel: LoginViewModel) {
        viewModel.loginLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> goToHomeScreen()
                ResponseStatus.ERROR -> showMessageError(viewState.error)
            }
        })
    }

    private fun showMessageError(error: Throwable?) {
        Toast.makeText(this, error?.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun goToHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun verifyFields(): Boolean {
        return if (edtEmail.text.isEmpty() || edtPass.text!!.isEmpty()) {
            if (edtEmail.text.isEmpty()) edtEmail.error = "Campo obrigatorio"
            if (edtPass.text!!.isEmpty()) edtPass.error = "Campo obrigatorio"
            false
        } else {
            true
        }
    }
}