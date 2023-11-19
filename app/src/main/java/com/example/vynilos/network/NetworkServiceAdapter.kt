package com.example.vynilos.network
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Artist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceAdapter {

    companion object {

        val BASE_URL = "http://34.69.228.4:3000"


        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
            build()
        }
    }

    fun getArtist(liveDataList: MutableLiveData<Artist>, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getArtist("/bands/$id")

            call.enqueue(object : Callback<Artist> {
                override fun onFailure(call: Call<Artist>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Artist>, response: Response<Artist>) {
                    Handler(Looper.getMainLooper()).post {
                        Log.i("getArtistAdapter", response.body().toString())
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }
}