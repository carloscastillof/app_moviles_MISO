package com.example.vynilos.views

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsMusiciansBinding
import com.example.vynilos.network.NetworkServiceAdapter
import com.example.vynilos.viewmodels.MusiciansViewModel
import com.example.vynilos.views.adapters.AlbumAdapter
import com.google.android.material.textfield.TextInputLayout

class AlbumsMusiciansActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumsMusiciansBinding
    private var serviceAdapter = NetworkServiceAdapter()
    private lateinit var albumAdapter: AlbumAdapter

    private lateinit var errorBorder: Drawable

    private lateinit var etAlbumId: EditText

    private lateinit var etAlbumIdLayout: TextInputLayout

    private lateinit var btnCancelar: Button
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsMusiciansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        etAlbumId = findViewById(R.id.etAlbumId)


        val viewModel = ViewModelProvider(this).get(MusiciansViewModel::class.java)
        var toNumberAlbumId : Number = 0
        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            toNumberAlbumId = (albumId.toInt())
        }

        btnCancelar = findViewById(R.id.btnCancelar)
        btnGuardar = findViewById(R.id.btnGuardar)

        setToolbarText()
        handleBackClick()

        btnCancelar.setOnClickListener {
            this.finish()
        }

        etAlbumIdLayout = findViewById(R.id.etAlbumIdLayout)

        setSaveButton()


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setSaveButton() {


        binding.btnGuardar.setOnClickListener {
            Log.i("Oprime boton guardar", btnGuardar.toString())

            val artistId = intent.getStringExtra("artistId")?.toInt()
            val albumId = etAlbumId.text.toString().toInt()
            Log.e("ArtistId", "ArtistID: ${artistId}")
            Log.e("AlbumId", "AlbumID: ${albumId}")
            if (artistId != null) {
                serviceAdapter.createAlbumToBand(artistId, albumId ) { statusCode ->
                    if (statusCode != 200) {
                        etAlbumIdLayout.error
                        etAlbumIdLayout.isErrorEnabled = true
                        etAlbumIdLayout.boxStrokeColor = getColor(R.color.red)
                    } else {
                        this.finish()
                    }
                }
            }

        }
    }


    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.tie_album_to_band)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    fun createTrackToAlbum(name: String, duration: String, id: Number){
        serviceAdapter.createTrackToAlbum(name, duration, id)
    }

}