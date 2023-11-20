package com.example.vynilos.views

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsBinding
import com.example.vynilos.enums.ROL
import com.example.vynilos.viewmodels.AlbumsActivityViewModel
import com.example.vynilos.views.adapters.AlbumAdapter

const val artistId = "com.example.vynilos.artistId"

class AlbumsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var adapter: AlbumAdapter

    private fun getRol(): ROL {
        val role = getSharedPreferences("user", MODE_PRIVATE).getString("role", "")
        if( !role.isNullOrBlank()){
            return ROL.valueOf(role)
        } else {
            return ROL.USUARIO
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarText()
        handleBackClick()
        initViewModel()
        initRecyclerView()
        bindMenuEvents()
        val agregarAlbumBtn = findViewById<Button>(R.id.agregaralbumbtn)

        agregarAlbumBtn.visibility = if (getRol() == ROL.COLECCIONISTA) View.VISIBLE else View.GONE

        val gotoMusiciansBtn = findViewById<Button>(R.id.list_musicians)
        gotoMusiciansBtn.setOnClickListener {
            this.navegarListadoMusicos()
        }
        val etSearch = findViewById<EditText>(R.id.etSearch)
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        agregarAlbumBtn.setOnClickListener {
            val intent = Intent(this, AlbumsCreateActivity::class.java)
            startActivity(intent)
        }
   }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.albums)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener {
            this.finish()
        }
    }

    private fun initRecyclerView() {
        adapter = AlbumAdapter()
        binding.rvAlbums.layoutManager = LinearLayoutManager(this)
        binding.rvAlbums.adapter = adapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(AlbumsActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            adapter.setAlbums(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
    }

    private fun bindMenuEvents() {
        val btnDetalleArtista: Button = findViewById(R.id.list_musicians)
        btnDetalleArtista.setOnClickListener {
            navegarListadoMusicos()
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    fun navegarListadoMusicos() {
        val intent = Intent(this, MusiciansActivity::class.java).apply {
        }
        startActivity(intent)
    }

    fun navegarAddAlbum() {}
}