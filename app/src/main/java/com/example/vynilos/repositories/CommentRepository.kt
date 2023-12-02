package com.example.vynilos.repositories



import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vynilos.models.Comment
import com.example.vynilos.network.NetworkServiceAdapter

class CommentRepository {

    private var serviceAdapter = NetworkServiceAdapter()

    fun getComment(id: Number, liveDataList: MutableLiveData<Comment>) {
        Log.i("getComment", id.toString())
        serviceAdapter.getComment(liveDataList, id)
    }

}