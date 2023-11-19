package com.example.vynilos.repositories

import android.util.Log
import com.example.vynilos.models.Artist
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.await

class ArtistRepository {
    private var apiService = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)
    public suspend fun getArtists(): List<Artist> {
        Log.i("Vinilos","Invocando listado de artistas")
        val data =  apiService.getMusicians().await()
        return if(data.isNotEmpty()) {
            Log.i("Artistas", data.toString())
            data
        } else{
            Log.i("errorGetMisicians","No se pudo cargar el listado de musicos")
            emptyList()
        }

    }

}