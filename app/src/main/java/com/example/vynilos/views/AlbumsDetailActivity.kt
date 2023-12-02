package com.example.vynilos.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.vynilos.databinding.ItemTrackBinding
import com.example.vynilos.viewmodels.TrackDetailViewModel
import com.example.vynilos.views.adapters.TrackAdapter
import com.google.android.material.tabs.TabLayout

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding
    private lateinit var trackbinding : ItemTrackBinding
    private val viewModel: AlbumDetailViewModel by viewModels()
    private val viewModelTrack: TrackDetailViewModel by viewModels()
    private lateinit var actionButton: Button
    private lateinit var actionButtonComentarios: Button
    private lateinit var adapter: TrackAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        trackbinding = ItemTrackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            initViewModel(albumId.toInt())
            bindAlbumDetailEvents(albumId)
        }
        handleBackClick()

        actionButton = findViewById(R.id.btn_tie_track_to_album)
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
                    /*var albumId = intent.getStringExtra("albumId")
                    if (albumId != null) {
                        Log.i("albumIdTrack", albumId.toString())
                        initViewModelTrack(albumId.toInt())
                    }

                    handleBackClick()*/
                    actionButton.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                    actionButtonComentarios.visibility = View.GONE

                    /*findViewById<RelativeLayout>(R.id.tracksContent).visibility = View.VISIBLE
                    findViewById<RelativeLayout>(R.id.comentariosContent).visibility = View.GONE*/

                } else if (tab?.text == "Comentarios") {

                    actionButton.visibility = View.GONE
                    actionButtonComentarios.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE

                    /*findViewById<RelativeLayout>(R.id.tracksContent).visibility = View.GONE
                    findViewById<RelativeLayout>(R.id.comentariosContent).visibility = View.VISIBLE*/

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener {
            this.finish()
        }
    }
    private fun bindAlbumDetailEvents(albumId: String) {
        val trackTiedToAlbumButton: Button = findViewById(R.id.btn_tie_track_to_album)
        trackTiedToAlbumButton.setOnClickListener { view ->
            openTrackTiedToAlbumView(view, albumId)
        }
        val commentTiedToAlbumButton: Button = findViewById(R.id.actionButtonComentarios)
        commentTiedToAlbumButton.setOnClickListener { view ->
            openCommentTiedToAlbumView(view, albumId)
        }
    }
    private fun openTrackTiedToAlbumView(view: View, albumId:String) {
        Log.i("tiedToAlbum", albumId)
        val intent = Intent(this, AlbumsTracksActivity::class.java).apply {
        }
        intent.putExtra("albumId", albumId )
        startActivity(intent)
    }
    private fun openCommentTiedToAlbumView(view: View, albumId:String) {
        Log.i("tiedToAlbumComment", albumId)
        val intent = Intent(this, AlbumsCommentsActivity::class.java).apply {
        }
        Log.i("intent", intent.toString())
        intent.putExtra("albumId", albumId )
        startActivity(intent)
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

    private fun initViewModelTrack(albumId: Number ) {
        val viewModelTrack = ViewModelProvider(this).get(TrackDetailViewModel::class.java)
        viewModelTrack.getLiveDataObserver().observe(this, Observer {
            Log.i("TrackName", trackbinding.tvTrackName.text.toString())
            trackbinding.tvTrackName.text = it.name
            trackbinding.tvTrackDuration.text = it.duration

        })
        viewModelTrack.makeApiCall(albumId)
    }


}
