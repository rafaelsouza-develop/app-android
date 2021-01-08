package br.com.app4pets.app.modules.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.app4pets.app.R
import androidx.lifecycle.Observer
import br.com.app4pets.app.data.network.models.RegisterRequest
import br.com.app4pets.app.data.network.models.RegisterResponse
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.modules.login.LoginActivity
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.edtEmail
import kotlinx.android.synthetic.main.activity_register.edtPass
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setListners()
        setupObserverViewState(viewModel)
    }

    private fun setupObserverViewState(viewModel: RegisterViewModel) {
        viewModel.registerLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.SUCCESS -> goToLoginScreen(viewState.data)
                ResponseStatus.ERROR -> showMessageError(viewState.error)
            }
        })
    }

    private fun goToLoginScreen(data: RegisterResponse?) {
        Toast.makeText(this, "Obrigado ${data?.user?.name}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showMessageError(error: Throwable?) {
        Toast.makeText(this, error?.localizedMessage, Toast.LENGTH_LONG).show()
    }

    private fun setListners() {
        /*edtBurthiday.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                edtBurthiday.showDatePicker(this)
                edtPass.requestFocus()
            }
        }*/

        btnRegister.setOnClickListener {
            if (verifyFields()) {
                viewModel.register(
                    RegisterRequest(
                        email = edtEmail.text.toString(),
                        password = edtPass.text.toString(),
                        name = edtName.text.toString(),
                        birthday = Date(edtBurthiday.text.toString())
                    )
                )
            }
        }
    }

    private fun verifyFields(): Boolean {
        return if (edtEmail.text.isEmpty() || edtPass.text!!.isEmpty() || edtName.text!!.isEmpty() || edtBurthiday.text!!.isEmpty()) {
            if (edtName.text.isEmpty()) edtEmail.error = "Campo obrigatorio"
            if (edtEmail.text.isEmpty()) edtEmail.error = "Campo obrigatorio"
            if (edtBurthiday.text!!.isEmpty()) edtPass.error = "Campo obrigatorio"
            if (edtPass.text!!.isEmpty()) edtPass.error = "Campo obrigatorio"
            false
        } else {
            true
        }
    }

    companion object {
        const val PATTERN_DATE_BD = "yyyy-MM-dd"
        const val PATTERN_DATE_HUMAN = "dd/MM/yyyy"
    }

}