package com.example.vynilos.repositories

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import com.example.vynilos.network.NetworkServiceAdapter.Companion.getRetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository {
    private var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)
    private var serviceAdapter = NetworkServiceAdapter()
    fun getAlbums(liveDataList: MutableLiveData<List<Album>>) {
        CoroutineScope(Dispatchers.IO).launch {
            var service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getAlbums("/albums")

            call.enqueue(object : Callback<List<Album>> {
                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(emptyList())
                    }
                }
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    //Return to main thread that draws the UI
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }

                }
            })
        }
    }

    fun getAlbum(id: Number, liveDataList: MutableLiveData<Album>) {
        CoroutineScope(Dispatchers.IO).launch {
            var service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getAlbum("/albums/" + id)

            call.enqueue(object : Callback<Album> {
                override fun onFailure(call: Call<Album>, t: Throwable) {
                    //#Need to figureout how to handle error
                }
                override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    Handler(Looper.getMainLooper()).post {
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }
    fun createAlbum(album: Album): Call<Album> {
        return serviceAdapter.createAlbum(album)
    }
}