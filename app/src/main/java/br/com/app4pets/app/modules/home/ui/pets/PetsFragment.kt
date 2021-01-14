package br.com.app4pets.app.modules.home.ui.pets

import android.content.Intent
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
import br.com.app4pets.app.modules.petcreate.PetCreateActivity
import br.com.app4pets.app.modules.petprofile.PetProfileActivity
import br.com.app4pets.app.util.extensions.hideKeyboard
import kotlinx.android.synthetic.main.fragment_pets.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PetsFragment : Fragment(), PetsAdapter.PetsAdapterListner {

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
        setListners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.listPets()
    }

    private fun setListners(){
        imgNewPet.setOnClickListener { openCreatePet() }
    }

    private fun setupObserverViewState(viewModel: PetsViewModel) {
        viewModel.petsLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.LOADING -> (activity as HomeActivity).showProgressDialog()
                ResponseStatus.UNLOADING -> (activity as HomeActivity).dismissProgressDialog()
                ResponseStatus.EMPTY_LIST -> setupEmptyList()
                ResponseStatus.SUCCESS -> setRecyclerViewList(viewState.data!!)
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
        (activity as HomeActivity).dismissProgressDialog()
        recyclerPets.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PetsAdapter(petsList, this@PetsFragment)
            activity?.hideKeyboard(this)
        }
    }

    override fun goToPetProfile(pet: Pet) {
        val intent = Intent(requireContext(), PetProfileActivity::class.java)
        intent.putExtra(PetProfileActivity.PET, pet)
        startActivity(intent)
    }

    private fun openCreatePet(){
        val intent = Intent(requireContext(), PetCreateActivity::class.java)
        startActivity(intent)
    }
}