package com.example.vynilos.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Track
import com.example.vynilos.network.NetworkServiceAdapter
class TrackRepository {
    private var serviceAdapter = NetworkServiceAdapter()

    fun getTrack(id: Number, liveDataList: MutableLiveData<Track>) {
        Log.i("getTrack", id.toString())
        serviceAdapter.getTrack(liveDataList, id)
    }
}