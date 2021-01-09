package br.com.app4pets.app.modules.home.ui.pets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.app4pets.app.R
import br.com.app4pets.app.models.Pet
import br.com.app4pets.app.models.ResponseStatus
import br.com.app4pets.app.modules.home.HomeActivity
import br.com.app4pets.app.util.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_pets.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetsFragment : Fragment() {

    private val viewModel: PetsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserverViewState(viewModel)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listPets()
    }

    private fun setupObserverViewState(viewModel: PetsViewModel) {
        viewModel.petsLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.LOADING -> (activity as HomeActivity).showProgressDialog()
                ResponseStatus.UNLOADING -> (activity as HomeActivity).dismissProgressDialog()
                ResponseStatus.EMPTY_LIST -> setupEmptyList()
                ResponseStatus.SUCCESS -> setRecyclerViewList(viewState.data!!.items)
                ResponseStatus.ERROR -> setupError()
            }
        })
    }

    private fun setupError() {
        (activity as HomeActivity).dismissProgressDialog()
        Toast.makeText(requireContext(), "DEU ERROR", Toast.LENGTH_LONG).show()
    }

    private fun setupEmptyList() {
        (activity as HomeActivity).dismissProgressDialog()
        Toast.makeText(requireContext(), "LISTA VEIO VAZIA", Toast.LENGTH_LONG).show()
    }

    private fun setRecyclerViewList(petsList: ArrayList<Pet>) {

        recyclerPets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PetsAdapter(petsList)
            activity?.hideKeyboard(this)
        }
    }


}