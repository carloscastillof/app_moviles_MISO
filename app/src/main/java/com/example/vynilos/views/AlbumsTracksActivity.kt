package com.example.vynilos.views

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsTracksBinding
import com.example.vynilos.network.NetworkServiceAdapter
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.google.android.material.textfield.TextInputEditText

class AlbumsTracksActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsTracksBinding
    private var serviceAdapter = NetworkServiceAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsTracksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        var toNumberAlbumId : Number = 0
        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            toNumberAlbumId = (albumId.toInt())
        }

        setToolbarText()
        handleBackClick()
        val postButton: Button = findViewById(R.id.btn_create_tie_track_to_album)

        postButton.setOnClickListener {
            val nameTxt : TextInputEditText = findViewById(R.id.txt_name)
            val durationTxt : TextInputEditText = findViewById(R.id.txt_duration)
            val name = nameTxt.text.toString()
            val duration = durationTxt.text.toString()
            createTrackToAlbum(name, duration, toNumberAlbumId)
            this.finish()
        }

    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.tie_track_to_album)
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