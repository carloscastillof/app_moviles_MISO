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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.viewmodels.TrackDetailViewModel
import com.example.vynilos.views.adapters.CommentAdapter
import com.example.vynilos.views.adapters.TrackAdapter
import com.google.android.material.tabs.TabLayout

class AlbumsDetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailAlbumBinding
    private val viewModel: AlbumDetailViewModel by viewModels()
    private val viewModelTrack: TrackDetailViewModel by viewModels()
    private lateinit var actionButton: Button
    private lateinit var actionButtonComentarios: Button
    private lateinit var adapter: TrackAdapter
    private lateinit var commentadapter: CommentAdapter
    private var isTracksTabInitialized = false
    private var isCommentTabInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            initViewModel(albumId.toInt())
        }
        handleBackClick()
        initRecyclerView()
        initCommentRecyclerView()

        actionButton = findViewById(R.id.btn_tie_track_to_album)
        actionButtonComentarios = findViewById(R.id.actionButtonComentarios)

        val tabLayout = binding.tabLayout

        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val roleName = sharedPreferences.getString("role", "")

        if (roleName == "COLECCIONISTA") {
            actionButton.visibility = View.VISIBLE
            actionButtonComentarios.visibility = View.GONE
            if (albumId != null) {
                bindAlbumDetailEvents(albumId)
            }
        } else {
            actionButton.visibility = View.GONE
            actionButtonComentarios.visibility = View.GONE
        }
        binding.rvComments.visibility = View.GONE
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "Tracks") {
                    binding.rvComments.visibility = View.GONE
                    actionButton.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                    actionButtonComentarios.visibility = View.GONE
                    if (!isTracksTabInitialized) {
                        if (albumId != null) {
                            initViewModel(albumId.toInt())
                            bindAlbumDetailEvents(albumId)
                            initRecyclerView()
                            isTracksTabInitialized = true
                        }
                    }
                    binding.rvTracks.visibility = View.VISIBLE
                } else if (tab?.text == "Comentarios") {
                    binding.rvTracks.visibility = View.GONE
                    actionButton.visibility = View.GONE
                    actionButtonComentarios.visibility = if (roleName == "COLECCIONISTA") View.VISIBLE else View.GONE
                    if (!isCommentTabInitialized) {
                        if (albumId != null) {
                            initViewModel(albumId.toInt())
                            bindAlbumDetailEvents(albumId)
                            initCommentRecyclerView()
                            isCommentTabInitialized = true
                        }
                    }
                    binding.rvComments.visibility = View.VISIBLE
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
        intent.putExtra("albumId", albumId)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        adapter = TrackAdapter()
        binding.rvTracks.layoutManager = LinearLayoutManager(this)
        binding.rvTracks.adapter = adapter
    }

    private fun initCommentRecyclerView() {
        Log.i("Comentario", "Entro al initCommentRecyclerView")
        commentadapter = CommentAdapter()
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = commentadapter
    }
    private fun initViewModel(albumId: Number ) {
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            adapter.setTracks(it.tracks.toList())
            adapter.notifyDataSetChanged()
            for (item in it.tracks) println(item.name)
            commentadapter.setComments(it.comments.toList())
            commentadapter.notifyDataSetChanged()
            for (item in it.comments) println(item.description)
            binding.title.text = it.name
            binding.gender.text = it.genre
            val year = it.releaseDate.substring(0, 4)
            binding.date.text = year
            Picasso.get().load(it.cover).into(binding.ivCover)
        })
        viewModel.makeApiCall(albumId)
    }



}
