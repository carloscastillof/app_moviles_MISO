package com.example.vynilos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Track
import com.example.vynilos.repositories.TrackRepository

class TrackDetailViewModel : ViewModel() {
    private var liveData: MutableLiveData<Track> = MutableLiveData()
    private val trackRepository = TrackRepository()

    fun getLiveDataObserver(): MutableLiveData<Track> {
        Log.i("livedata", liveData.toString())
        return liveData
    }

    fun makeApiCall(id: Number) {
        trackRepository.getTrack(id, liveData)
    }
}