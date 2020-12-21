package br.com.app4pets.app.modules.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.app4pets.app.R
import br.com.app4pets.app.modules.register.RegisterActivity
import br.com.app4pets.app.network.models.LoginRequest
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setListners()

    }

    private fun setListners(){
        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if(verifyFields()){
                viewModel.login(LoginRequest(edtEmail.text.toString(), edtPass.text.toString()))
            }
        }
    }

    private fun verifyFields(): Boolean {
        return if (edtEmail.text.isEmpty() || edtPass.text!!.isEmpty()){
            if(edtEmail.text.isEmpty()) edtEmail.error = "Campo obrigatorio"
            if(edtPass.text!!.isEmpty()) edtPass.error = "Campo obrigatorio"
            false
        }else{
            true
        }
    }
}