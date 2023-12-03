package com.example.vynilos.network
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Comment
import com.example.vynilos.models.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    fun getTrack(liveDataList: MutableLiveData<Track>, albumId: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getTrack("/albums/$albumId/tracks")

            call.enqueue(object : Callback<Track> {
                override fun onFailure(call: Call<Track>, t: Throwable) {
                }

                override fun onResponse(call: Call<Track>, response: Response<Track>) {
                    Handler(Looper.getMainLooper()).post {
                        Log.i("getTrackAdapter", response.body().toString())
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun getComment(liveDataList: MutableLiveData<Comment>, albumId: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val call = service.getComment("/albums/$albumId/comments")

            call.enqueue(object : Callback<Comment> {
                override fun onFailure(call: Call<Comment>, t: Throwable) {
                }

                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    Handler(Looper.getMainLooper()).post {
                        Log.i("getCommentAdapter", response.body().toString())
                        liveDataList.postValue(response.body())
                    }
                }
            })
        }
    }

    fun createAlbum(album: Album): Call<Album> {
        Log.i("album_creado", album.toString())
        val service = getRetrofitInstance().create(ApiService::class.java)
        return service.createAlbum("/albums", album.jsonPostString())
    }


    fun createCommentToAlbum(description: String, rating: Number, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val comment = Comment(description = description, rating = rating)

            val call = service.createCommentToAlbum("/albums/$id/comments",comment)

            call.enqueue(object : Callback<Comment> {
                override fun onFailure(call: Call<Comment>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    val addedComment = response.body()
                    println(addedComment)
                }
            })
        }
    }

    fun createTrackToAlbum(name: String, duration: String, id: Number) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)
            val track = Track(name = name, duration = duration)

            val call = service.createTrackToAlbum("/albums/$id/tracks",track)

            call.enqueue(object : Callback<Track> {
                override fun onFailure(call: Call<Track>, t: Throwable) {
                    //#Need to figureout how to handle error
                }

                override fun onResponse(call: Call<Track>, response: Response<Track>) {
//                    Handler(Looper.getMainLooper()).post {
//                        liveDataList.postValue(response.body())
//                    }
                    val addedTrack = response.body()
                    println(addedTrack)
                }
            })
        }
    }

    fun createAlbumToBand(bandId: Number, albumId: Number, callback: (Int) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val service = getRetrofitInstance().create(ApiService::class.java)

            val call = service.createAlbumToBand("/bands/$bandId/albums/$albumId")
            val request = call.request()
            val requestBody = request.body()
            val requestBodyString = requestBody?.toString() ?: ""

            Log.d("Request", "Request URL: ${request.url()}")
            Log.d("Request", "Request Method: ${request.method()}")
            Log.d("Request", "Request Headers: ${request.headers()}")
            Log.d("Request", "Request Body: $requestBodyString")

            call.enqueue(object : Callback<Album> {
                override fun onFailure(call: Call<Album>, t: Throwable) {
                    Log.e("NetworkError", "Error occurred: ${t.message}")
                    callback.invoke(0)
                }

                override fun onResponse(call: Call<Album>, response: Response<Album>) {
                    if (response.isSuccessful) {
                        val addedTrack = response.body()
                        Log.d("NetworkSuccess", "Track added: $addedTrack")
                        callback.invoke(response.code())
                    } else {
                        Log.e("NetworkError", "Error: ${response.code()}")
                        callback.invoke(response.code())
                        // Aquí puedes manejar el error de la manera que prefieras
                    }
                }
            })
        }
    }
}