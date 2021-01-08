package br.com.app4pets.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.app4pets.app.R
import dmax.dialog.SpotsDialog

open class BaseActivity : AppCompatActivity() {
    private lateinit var dialog : SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showProgressDialog() {
        dialog =  SpotsDialog.Builder()
            .setContext(this)
            .setMessage(R.string.waiting)
            .setCancelable(false)
            .build() as SpotsDialog

        dialog.show()
    }

    fun dismissProgressDialog() {
        dialog.dismiss()
    }
}