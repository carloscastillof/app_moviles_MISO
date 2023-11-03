package com.example.vynilos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.vynilos.enums.ROL
import com.example.vynilos.views.AlbumsActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindMenuEvents()
    }

    private fun bindMenuEvents() {
        val btnColeccionista: Button = findViewById(R.id.btn_coleccionista)
        btnColeccionista.setOnClickListener { view ->
            saveRol(ROL.COLECCIONISTA)
            openAlbumListView(view)
        }
        val btnUsuario: Button = findViewById(R.id.btn_usuario)
        btnUsuario.setOnClickListener { view ->
            saveRol(ROL.USUARIO)
            openAlbumListView(view)
        }
    }

    private fun saveRol(rol: ROL) {
        val sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("role", rol.name)
        editor.apply()
    }

    private fun openAlbumListView(view: View) {
        val intent = Intent(this, AlbumsActivity::class.java).apply {
        }
        startActivity(intent)
    }
}