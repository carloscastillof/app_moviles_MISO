package com.example.vynilos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.vynilos.views.AlbumsActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindMenuEvents()
    }

    private fun bindMenuEvents() {
        val albumsMenuButton: Button = findViewById(R.id.btn_coleccionista)
        albumsMenuButton.setOnClickListener { view ->
            openAlbumListView(view)
        }
        val artistMenuButton: Button = findViewById(R.id.btn_usuario)
        artistMenuButton.setOnClickListener { view ->
            openAlbumListView(view)
        }
    }

    private fun openAlbumListView(view: View) {
        val intent = Intent(this, AlbumsActivity::class.java).apply {
        }
        startActivity(intent)
    }
}