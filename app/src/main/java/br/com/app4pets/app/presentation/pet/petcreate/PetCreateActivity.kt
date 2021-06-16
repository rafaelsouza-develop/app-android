package br.com.app4pets.app.presentation.pet.petcreate

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import br.com.app4pets.app.R
import br.com.app4pets.app.base.BaseActivity
import br.com.app4pets.app.data.network.models.PetRequest
import br.com.app4pets.app.domain.models.Pet
import br.com.app4pets.app.domain.models.ResponseStatus
import br.com.app4pets.app.presentation.auth.register.RegisterActivity
import br.com.app4pets.app.util.extensions.showDatePicker
import br.com.app4pets.app.util.extensions.toStringFormat
import kotlinx.android.synthetic.main.activity_pet_create.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.*
import java.util.*


class PetCreateActivity : BaseActivity() {

    private val viewModel: PetCreateViewModel by viewModel()

    private var bitmap: Bitmap? = null
    private val BREED = arrayOf(
        "Akita",
        "Basset hound",
        "Beagle",
        "Cane corso",
        "Chihuahua",
        "Chow chow",
        "Dachshund",
        "Dálmata",
        "Lulu da pomerânia",
        "Pastor alemão",
        "Pit bull",
        "Rottweiler",
        "Shih tzu",
        "Yorkshire"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_create)
        setupAppBar(toolbar)
        setListners()
        populateAutoCompleteBreed()
        setupObserverViewState(viewModel)
    }

    private fun setupAppBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
            title = ""
        }
    }

    private fun populateAutoCompleteBreed() {
        val mAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line, BREED
        )
        edtAutoCompleteBreed.setAdapter(mAdapter)
    }

    private fun setListners() {
        imgPicture.setOnClickListener { selectImage(this) }

        edtPetBurthiday.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                edtPetBurthiday.showDatePicker(this)
            }
        }

        btnCreatePet.setOnClickListener { createPet() }
    }

    private fun createPet() {

        var image = buildImageBodyPart("thumbnail", bitmap!!)
        if (verifyFields()) viewModel.createPet(
            image, PetRequest(
                edtName.text.toString(),
                edtAutoCompleteBreed.text.toString(),
                Date(edtPetBurthiday.text.toString()).toStringFormat(
                    RegisterActivity.PATTERN_DATE_BD
                )!!,
                edtPetColor.text.toString()
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObserverViewState(viewModel: PetCreateViewModel) {
        viewModel.createPetLiveData.observe(this, Observer { viewState ->
            when (viewState.status) {
                ResponseStatus.LOADING -> showProgressDialog()
                ResponseStatus.UNLOADING -> dismissProgressDialog()
                ResponseStatus.SUCCESS -> createPetSuccess(viewState.data!!)
                ResponseStatus.ERROR -> showError()
                else -> dismissProgressDialog()
            }
        })
    }

    private fun createPetSuccess(pet: Pet) {
        dismissProgressDialog()
        clearView()
        Toast.makeText(this, pet.name, Toast.LENGTH_LONG).show()
    }

    private fun clearView() {
        imgPicture.setImageDrawable(resources.getDrawable(R.drawable.ic_picture))
        bitmap = null
        edtName.setText("")
        edtAutoCompleteBreed.setText("")
        edtPetBurthiday.setText("")
        edtPetColor.setText("")
    }

    private fun showError() {
        dismissProgressDialog()
        Toast.makeText(this, "Deu Erro", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                GALLERY_IMAGE -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val path: Uri = data.data!!

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(contentResolver, path)
                        imgPicture.setImageBitmap(bitmap)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
                TAKE_IMAGE -> if (resultCode == Activity.RESULT_OK && data != null) {
                    bitmap = data.extras?.get("data") as Bitmap
                    imgPicture.setImageBitmap(bitmap)
                }
            }
        }
    }

    private fun selectImage(context: Context) {
        val options =
            arrayOf<CharSequence>(TAKE_A_PICTURE, GET_PICTURE_FROM_GALLERY, CANCEL)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Escolha uma foto")
        builder.setItems(options) { dialog, item ->
            if (options[item] == TAKE_A_PICTURE) {
                val takePicture =
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePicture, TAKE_IMAGE)
            } else if (options[item] == GET_PICTURE_FROM_GALLERY) {
                selectImage()
            } else if (options[item] == CANCEL) {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, GALLERY_IMAGE)
    }

    private fun convertBitmapToFile(fileName: String, bitmap: Bitmap): File {
        val file = File(this.cacheDir, fileName)
        file.createNewFile()

        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
        val bitMapData = bos.toByteArray()

        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        try {
            fos?.write(bitMapData)
            fos?.flush()
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    private fun buildImageBodyPart(fileName: String, bitmap: Bitmap): MultipartBody.Part {
        val leftImageFile = convertBitmapToFile(fileName, bitmap)
        val reqFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), leftImageFile)
        return MultipartBody.Part.createFormData(fileName, leftImageFile.name, reqFile)
    }

    private fun verifyFields(): Boolean {
        return if (edtName.text.isEmpty() || edtAutoCompleteBreed.text.isEmpty() || edtPetBurthiday.text.isEmpty() || edtPetColor.text!!.isEmpty()) {
            if (edtName.text.isEmpty()) edtName.error = "Campo obrigatorio"
            if (edtAutoCompleteBreed.text!!.isEmpty()) edtAutoCompleteBreed.error =
                "Campo obrigatorio"
            if (edtPetBurthiday.text.isEmpty()) edtPetBurthiday.error = "Campo obrigatorio"
            if (edtPetColor.text!!.isEmpty()) edtPetColor.error = "Campo obrigatorio"
            false
        } else {
            true
        }
    }

    companion object {
        const val TAKE_IMAGE = 1
        const val GALLERY_IMAGE = 0
        const val TAKE_A_PICTURE = "Tirar uma foto"
        const val GET_PICTURE_FROM_GALLERY = "Escolher foto na galeria"
        const val CANCEL = "cancelar"
    }
}