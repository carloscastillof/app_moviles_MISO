package com.example.vynilos.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Album
import com.example.vynilos.repositories.AlbumRepository

class AlbumsActivityViewModel(): ViewModel() {
    lateinit var liveDataList: MutableLiveData<List<Album>>
    private val albumsRepository = AlbumRepository()

    init {
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Album>> {
        return liveDataList
    }

    fun makeApiCall() {
        albumsRepository.getAlbums(liveDataList)
    }
}