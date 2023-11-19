package com.example.vynilos.views

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
        Log.i("Artist_onCreate",artistId.toString())
        if (artistId != null) {
            initViewModel(artistId.toInt())
        }
        handleBackClick()
    }


    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
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