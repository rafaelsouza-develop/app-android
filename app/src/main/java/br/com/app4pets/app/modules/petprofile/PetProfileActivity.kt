package br.com.app4pets.app.modules.petprofile

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.app4pets.app.R
import br.com.app4pets.app.models.Pet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pet_profile.*
import kotlinx.android.synthetic.main.content_scrolling.*

class PetProfileActivity : AppCompatActivity() {

    private var pet: Pet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_profile)

        intent.extras?.let { extras ->
            pet = extras.getParcelable(PET)
            pet?.let { pet ->
                setView(pet)
            }
        }
        setupAppBar(toolbar)
    }

    private fun setView(pet: Pet) {
        Picasso.get().load(pet.thumbnail).into(imgProfilePet)
        txtBreed.text = pet.breed
        txtGenre.text = pet.genre
        txt_name_pet.text = pet.name
    }

    private fun setupAppBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = pet?.name
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val PET = "pet"
    }
}