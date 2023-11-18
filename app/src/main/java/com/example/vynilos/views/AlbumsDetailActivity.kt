package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.squareup.picasso.Picasso
import android.widget.Button
import com.example.vynilos.viewmodels.ArtistDetailViewModel
import com.google.android.material.tabs.TabLayout

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding
    private val viewModel: AlbumDetailViewModel by viewModels()
    private lateinit var actionButton: Button
    private lateinit var actionButtonComentarios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            initViewModel(albumId.toInt())
        }
        handleBackClick()

        // Ejemplo de prueba Daniel

        // val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        // val roleName = sharedPreferences.getString("role", "")
        // val rolTextView = findViewById<TextView>(R.id.rol)
        // rolTextView.text = roleName

        actionButton = findViewById(R.id.actionButton)
        actionButtonComentarios = findViewById(R.id.actionButtonComentarios)

        val tabLayout = binding.tabLayout

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val roleName = sharedPreferences.getString("role", "")

        if (roleName == "COLECCIONISTA") {
            actionButton.visibility = View.VISIBLE
            actionButtonComentarios.visibility = View.VISIBLE
        } else {
            actionButton.visibility = View.GONE
            actionButtonComentarios.visibility = View.GONE
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "Tracks") {
                    actionButton.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                    actionButtonComentarios.visibility = View.GONE
                } else if (tab?.text == "Comentarios") {
                    actionButton.visibility = View.GONE
                    actionButtonComentarios.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            binding.title.text = it.name
            binding.gender.text = it.genre

            val year = it.releaseDate.substring(0, 4)
            binding.date.text = year

            Picasso.get().load(it.cover).into(binding.ivCover)
        })

        viewModel.makeApiCall(albumId)
    }

}
