package com.example.vynilos.views

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityCreateAlbumBinding
import com.example.vynilos.models.Album
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class AlbumsCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAlbumBinding

    private lateinit var errorBorder: Drawable

    private lateinit var etTituloLayout: TextInputLayout

    private lateinit var etTitulo: EditText
    private lateinit var etDescripcion: EditText
    private lateinit var etFechaLanzamiento: EditText
    private lateinit var etGenero: EditText
    private lateinit var etSelloDiscografico: EditText
    private lateinit var etCoverImageUrl : EditText
    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateAlbumBinding.inflate(layoutInflater)
        setToolbarText()
        handleBackClick()
        setContentView(binding.root)

        // Inicializar vistas
        etTitulo = findViewById(R.id.etTitulo)
        etTituloLayout = findViewById(R.id.etTituloLayout)

        etTitulo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            @RequiresApi(Build.VERSION_CODES.M)
            override fun afterTextChanged(s: Editable?) {
                // Verifica la longitud del texto y aplica el borde rojo si excede los 50 caracteres
                if (s?.length ?: 0 > 50) {
                    etTituloLayout.error = "El título no puede tener más de 50 caracteres"
                    etTituloLayout.isErrorEnabled = true
                    etTituloLayout.boxStrokeColor = getColor(R.color.red)
                } else {
                    etTituloLayout.error = null
                    etTituloLayout.isErrorEnabled = false
                    etTituloLayout.boxStrokeColor = getColor(R.color.black)
                }
            }
        })

        etDescripcion = findViewById(R.id.etDescripcion)
        etFechaLanzamiento = findViewById(R.id.etFechaLanzamiento)
        etGenero = findViewById(R.id.etGenero)
        etSelloDiscografico = findViewById(R.id.etSelloDiscografico)
        etCoverImageUrl = findViewById(R.id.etCoverImageUrl)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnGuardar = findViewById(R.id.btnGuardar)

        // Configurar clic en botón Cancelar
        btnCancelar.setOnClickListener {
            this.finish()
        }

        setSaveButton()
    }
    private fun setSaveButton() {
        binding.btnGuardar.setOnClickListener {
            Log.i("Oprime boton guardar", btnGuardar.toString())
            processForm()
        }
    }
    private fun validateDateFormat(date: String): Boolean {
        val regex = """^\d{4}-\d{2}-\d{2}$""".toRegex()
        return regex.matches(date)
    }

    private fun validateGenre(genre: String): Boolean {
        return genre.equals("Salsa", ignoreCase = true) || genre.equals("Classical", ignoreCase = true) || genre.equals("Rock", ignoreCase = true) || genre.equals("Folk", ignoreCase = true)
    }

    private fun validateRecordLabel(recordLabel: String): Boolean {
        return recordLabel.equals("EMI", ignoreCase = true) || recordLabel.equals("Sony Music", ignoreCase = true) || recordLabel.equals("Discos Fuentes", ignoreCase = true) || recordLabel.equals("Elektra", ignoreCase = true) || recordLabel.equals("Fania Records", ignoreCase = true)
    }

    private fun processForm(){

        val view= this
        val titulo = etTitulo.text.toString()
        val descripcion = etDescripcion.text.toString()
        val fechaLanzamiento = etFechaLanzamiento.text.toString()
        val genero = etGenero.text.toString()
        val cover = etCoverImageUrl.text.toString()
        val selloDiscografico = etSelloDiscografico.text.toString()

        val album = Album(
            id = null,
            name=titulo,
            description = descripcion,
            cover = cover,
            genre = genero,
            recordLabel = selloDiscografico,
            releaseDate = fechaLanzamiento,
            tracks = emptyArray(),
            comments = emptyArray()
        )
        if (!validateDateFormat(fechaLanzamiento)) {
            etFechaLanzamiento.error = "Formato de fecha no válido. Utiliza el formato yyyy-mm-dd."
            return
        }

        // Validar género
        if (!validateGenre(genero)) {
            etGenero.error = "Género no válido. Por favor, elige entre Salsa, Classical, Rock o Folk."
            return
        }

        // Validar sello discográfico
        if (!validateRecordLabel(selloDiscografico)) {
            etSelloDiscografico.error = "Sello discográfico no válido. Por favor, elige entre EMI, Sony Music, Discos Fuentes, Elektra o Fania Records."
            return
        }
        Log.i("JSON enviado", album.jsonPostString().toString())
        val call = album.create()
        Log.i("call creado", call.toString())

        call.enqueue(object : Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                Log.i("onFailure call", call.toString())
                //#Need to figureout how to handle error
            }

            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                Log.i("onResponse call", call.toString())

                if (response.isSuccessful) {
                    val album = response.body()
                    Log.i("response_create_album", album.toString())

                    val intent = Intent(view, AlbumsDetailActivity::class.java)
                    intent.putExtra("albumId", album?.id?.toString())
                    view.startActivity(intent)
                } else {
                    Log.e("onResponse error", "Error en la respuesta: ${response.code()}")
                }
            }
        })
    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.albums)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener {
            this.finish()
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}