package com.example.vynilos.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsBinding
import com.example.vynilos.viewmodels.AlbumsActivityViewModel
import com.example.vynilos.views.adapters.AlbumAdapter

class AlbumsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlbumsBinding
    private lateinit var adapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbarText()
        handleBackClick()
        initViewModel()
        initRecyclerView()

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
   }

    private fun setToolbarText() {
        binding.toolbar.toolbarText.text = getString(R.string.albums)
    }

    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { view ->
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

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}