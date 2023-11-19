package com.example.vynilos.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityAlbumsBinding
import com.example.vynilos.databinding.ActivityMusiciansBinding
import com.example.vynilos.viewmodels.AlbumsActivityViewModel
import com.example.vynilos.viewmodels.MusiciansViewModel
import com.example.vynilos.views.adapters.AlbumAdapter
import com.example.vynilos.views.adapters.MusicianAdapter
import org.w3c.dom.Text

class MusiciansActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusiciansBinding
    private val adapterMusicianList = MusicianAdapter(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        changeTitle()
        handleBackClick()
        initViewModel()
        initMusiciansListAdapter()
    }

    private fun initBinding() {
        binding = ActivityMusiciansBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun changeTitle(title:String="Artistas"){
        binding.toolbar.toolbarText.text = title;
    }
    private fun handleBackClick() {
        binding.toolbar.leftIcon.setOnClickListener { _ ->
            finish()
        }
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MusiciansViewModel::class.java)
        viewModel.getArtists().observe(this, Observer{
            adapterMusicianList.setArtists(it)
            adapterMusicianList.notifyDataSetChanged()
        })
    }
    private fun initMusiciansListAdapter() {

        binding.rvMusicians.layoutManager = LinearLayoutManager(this)
        binding.rvMusicians.adapter = adapterMusicianList

    }
}