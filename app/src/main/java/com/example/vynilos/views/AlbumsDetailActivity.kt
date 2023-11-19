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
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.models.Track
import com.google.android.material.tabs.TabLayout
import com.example.vynilos.views.adapters.TrackAdapter

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding
    private val viewModel: AlbumDetailViewModel by viewModels()
    private lateinit var actionButton: Button
    private lateinit var actionButtonComentarios: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val albumId = intent.getStringExtra("albumId") ?: ""

        if (albumId.isNotEmpty()) {
            initViewModel(albumId.toInt())
        }
        handleBackClick()

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

                    findViewById<RelativeLayout>(R.id.tracksContainer).visibility = View.VISIBLE
                    findViewById<RelativeLayout>(R.id.comentariosContainer).visibility = View.GONE

                    actionButton.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                    actionButtonComentarios.visibility = View.GONE

                    updateTrackCard(albumId)

                } else if (tab?.text == "Comentarios") {

                    findViewById<RelativeLayout>(R.id.tracksContainer).visibility = View.GONE
                    findViewById<RelativeLayout>(R.id.comentariosContainer).visibility = View.VISIBLE

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

    private fun updateTrackCard(albumId: String) {

        val trackAdapter = TrackAdapter()

        val tracksList: List<Track> = obtenerTracksDelAlbum(albumId)

        trackAdapter.setTracks(tracksList)

        val recyclerView: RecyclerView = binding.cardTracks.findViewById(R.id.recyclerViewTracks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackAdapter
    }

    private fun obtenerTracksDelAlbum(albumId: String): List<Track> {

        return emptyList()

    }

}
