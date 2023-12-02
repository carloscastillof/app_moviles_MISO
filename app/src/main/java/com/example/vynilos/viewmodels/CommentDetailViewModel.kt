package com.example.vynilos.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Comment
import com.example.vynilos.repositories.CommentRepository

class CommentDetailViewModel: ViewModel() {
    private var liveData: MutableLiveData<Comment> = MutableLiveData()
    private val commentRepository = CommentRepository()

    fun getLiveDataObserver(): MutableLiveData<Comment> {
        Log.i("livedata", liveData.toString())
        return liveData
    }

    fun makeApiCall(id: Number) {
        commentRepository.getComment(id, liveData)
    }

}

