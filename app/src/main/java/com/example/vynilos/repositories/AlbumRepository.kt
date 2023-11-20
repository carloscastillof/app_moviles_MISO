package com.example.vynilos.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository {
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)
    private var serviceAdapter = NetworkServiceAdapter()
    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        val call = service.getAlbums("/albums")
        Log.d("ApiService", "URL para getAlbums: /albums")
        call.enqueue(object : Callback<List<Album>> {
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                t.printStackTrace()
                Log.e("AlbumRepository", "Error en getAlbums: " + t.message)
                liveDataList.postValue(emptyList())
            }
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                liveDataList.postValue(response.body())
            }
        })
    }

    fun getAlbum(id: Number, liveDataList: MutableLiveData<Album>) {
        val call = service.getAlbum("/albums/" + id)

        call.enqueue(object : Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                //#Need to figureout how to handle error
            }
            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                liveDataList.postValue(response.body())
            }
        })
    }
    fun createAlbum(album: Album): Call<Album> {
        return serviceAdapter.createAlbum(album)
    }
}