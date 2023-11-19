package com.example.vynilos.views

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

        // Configurar clic en botón Guardar
        btnGuardar.setOnClickListener {
            // Aquí puedes obtener los valores de los EditText
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
                releaseDate = fechaLanzamiento
            )

            val call = album.create()

            call.enqueue(object : Callback<Album> {
                override fun onFailure(call: Call<Album>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    view.finish()
                    val intent = Intent(view, AlbumsDetailActivity::class.java)
                    intent.putExtra("albumId", response.body()?.id?.toString())
                    view.startActivity(intent)
                }
            })
        }
    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.albums)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

}