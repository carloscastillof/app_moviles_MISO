package com.example.vynilos.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Artist
import com.example.vynilos.network.NetworkServiceAdapter

class ArtistsRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getArtist(id: Number, liveDataList: MutableLiveData<Artist>) {
        Log.i("getArtist", id.toString())
        serviceAdapter.getArtist(liveDataList, id)
    }
}