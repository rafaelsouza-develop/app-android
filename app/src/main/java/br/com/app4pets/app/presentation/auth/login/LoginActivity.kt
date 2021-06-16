package br.com.app4pets.app.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.app4pets.app.R
import br.com.app4pets.app.base.BaseActivity
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.presentation.home.HomeActivity
import br.com.app4pets.app.presentation.auth.register.RegisterActivity
import br.com.app4pets.app.data.network.models.LoginRequest
import br.com.app4pets.app.util.validatehelper.ValidateHelperImpl.isValidEmail
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edtEmail
import kotlinx.android.synthetic.main.activity_login.edtPass

class LoginActivity : BaseActivity() {

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
                ResponseStatus.LOADING -> showProgressDialog()
                ResponseStatus.UNLOADING -> dismissProgressDialog()
                ResponseStatus.SUCCESS -> goToHomeScreen()
                ResponseStatus.ERROR -> showMessageError()
                else -> dismissProgressDialog()
            }
        })
    }

    private fun showMessageError() {
        dismissProgressDialog()
        Toast.makeText(this, getString(R.string.message_error_invalid_login), Toast.LENGTH_LONG)
            .show()
    }

    private fun goToHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun verifyFields(): Boolean {
        return if (edtEmail.text.isEmpty() || edtPass.text!!.isEmpty() || !isValidEmail(edtEmail.text.toString())) {
            if (!isValidEmail(edtEmail.text.toString())) edtEmail.error = "Email com formato invalido"
            if (edtEmail.text.isEmpty()) edtEmail.error = "Campo obrigatorio"
            if (edtPass.text!!.isEmpty()) edtPass.error = "Campo obrigatorio"
            false
        } else {
            true
        }
    }

}