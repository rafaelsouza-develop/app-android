package br.com.app4pets.app.modules.home

import android.os.Bundle
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import br.com.app4pets.app.R
import br.com.app4pets.app.base.BaseActivity
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.modules.login.LoginViewModel

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        
        navView.setupWithNavController(navController)
    }


}