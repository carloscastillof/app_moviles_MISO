package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Album
import com.example.vynilos.repositories.AlbumRepository

class AlbumDetailViewModel: ViewModel() {
    lateinit var liveData: MutableLiveData<Album>
    private val albumsRepository = AlbumRepository()

    init {
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<Album> {
        return liveData
    }

    fun makeApiCall(id: Number) {
        albumsRepository.getAlbum(id, liveData)
    }

}