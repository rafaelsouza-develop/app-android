package br.com.app4pets.app.modules.home.ui.pets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.app4pets.app.R
import br.com.app4pets.app.modules.home.ui.notifications.NotificationsViewModel
import br.com.app4pets.app.modules.register.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetsFragment : Fragment() {

    private val viewModel: PetsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}