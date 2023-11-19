package com.example.vynilos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Artist
import com.example.vynilos.repositories.ArtistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MusiciansViewModel: ViewModel() {
    private val artistRepo = ArtistRepository()
    private var artists: MutableLiveData<List<Artist>> = MutableLiveData<List<Artist>>()

    init{
        this.getMusicians()
    }

    public fun getArtists(): MutableLiveData<List<Artist>> {
        return this.artists
    }
    private fun getMusicians(){
        CoroutineScope(Dispatchers.IO).launch {
            val artistsResponse = artistRepo.getArtists()

            if(artistsResponse.isNotEmpty()){
                artists.postValue(artistsResponse);
                Log.i("artists","Cargo el listado de artistas")
            }else{
                artists.postValue(emptyList())
                Log.i("artists","Error al cargar el listado de artistas")
            }
        }


    }

}