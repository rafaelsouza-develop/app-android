package br.com.app4pets.app.presentation.home.ui.pets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.app4pets.app.R
import br.com.app4pets.app.domain.models.Pet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pets.view.*

class PetsAdapter(private val pets: ArrayList<Pet>, private val listner: PetsAdapterListner) :
    RecyclerView.Adapter<PetsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pets, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = pets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = pets[position]

        with(holder.itemView) {
            petBreed.text = pet.breed
            petName.text = pet.name

            pet.thumbnail.let {
                Picasso.get()
                    .load(pet.thumbnail)
                    .placeholder(R.drawable.foto_pet)
                    .into(imgPictureProfilePet)
            }

            setOnClickListener {
                listner.goToPetProfile(pet)
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface PetsAdapterListner{
        fun goToPetProfile(pet:Pet)
    }
}