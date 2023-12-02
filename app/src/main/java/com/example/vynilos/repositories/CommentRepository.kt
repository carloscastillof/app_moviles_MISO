package com.example.vynilos.repositories


import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Comment
import com.example.vynilos.network.ApiService
import com.example.vynilos.network.NetworkServiceAdapter
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentRepository {

    private var serviceAdapter = NetworkServiceAdapter()

    fun getComment(id: Number, liveData: MutableLiveData<Comment>) {
        CoroutineScope(Dispatchers.IO).launch {
            var service = NetworkServiceAdapter.getRetrofitInstance().create(ApiService::class.java)
            val call = service.getComment("/comment/$id")

            call.enqueue(object : Callback<Comment> {
                override fun onFailure(call: Call<Comment>, t: Throwable) {
                    // Manejar el fallo si es necesario
                }

                override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                    Handler(Looper.getMainLooper()).post {
                        liveData.postValue(response.body())
                    }
                }
            })
        }
    }
    fun createComment(comment: Comment): Call<Comment> {
        return serviceAdapter.createComment(comment)
    }

}