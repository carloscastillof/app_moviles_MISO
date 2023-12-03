package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityArtistsDetailBinding
import com.example.vynilos.viewmodels.ArtistDetailViewModel
import com.squareup.picasso.Picasso

class ArtistDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityArtistsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val artistId = intent.getStringExtra(artistId)

        val agregarAlbumBanda = findViewById<Button>(R.id.btn_tie_album_to_band)

        agregarAlbumBanda.setOnClickListener {
            val intent = Intent(this, AlbumsMusiciansActivity::class.java).apply {
                putExtra("artistId", artistId) // Agregar el ID del artista al Intent para la siguiente actividad
            }
            startActivity(intent)
        }


        Log.i("Artist_onCreate",artistId.toString())
        if (artistId != null) {
            initViewModel(artistId.toInt())
        }
        handleBackClick()
    }


    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener {
            this.finish()
        }
    }

    private fun initViewModel(artistId: Number ) {
        val viewModel = ViewModelProvider(this).get(ArtistDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            binding.title.text = it.name
            binding.tvDescription.text = it.description
            binding.date.text = it.parsedCreationDate()
            Picasso.get().load(it.image).into(binding.ivCover)
        }

        viewModel.makeApiCall(artistId)
    }
}