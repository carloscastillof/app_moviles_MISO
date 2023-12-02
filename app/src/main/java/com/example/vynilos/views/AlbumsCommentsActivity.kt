package com.example.vynilos.views

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsCommentsBinding
import com.example.vynilos.network.NetworkServiceAdapter
import com.example.vynilos.viewmodels.AlbumDetailViewModel
import com.google.android.material.textfield.TextInputEditText

class AlbumsCommentsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsCommentsBinding
    private var serviceAdapter = NetworkServiceAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this).get(AlbumDetailViewModel::class.java)
        var toNumberAlbumId : Number = 0
        val albumId = intent.getStringExtra("albumId")
        if (albumId != null) {
            toNumberAlbumId = (albumId.toInt())
        }

        setToolbarText()
        handleBackClick()
        val postButton: Button = findViewById(R.id.btn_create_comment_to_album)

        postButton.setOnClickListener {
            val descriptionTxt : TextInputEditText = findViewById(R.id.txt_descripcion)
            val ratingTxt : TextInputEditText = findViewById(R.id.txt_puntuacion)
            val description = descriptionTxt.text.toString()
            val ratingString = ratingTxt.text.toString()
            val rating: Number = try {
                ratingString.toInt()
            } catch (e: NumberFormatException) {
                0
            }
            createCommentToAlbum(description, rating, toNumberAlbumId)
            this.finish()
        }

    }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.tie_comment)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
            this.finish()
        }
    }

    fun createCommentToAlbum(description: String, rating: Number, id: Number){
        serviceAdapter.createCommentToAlbum(description, rating, id)
    }
}