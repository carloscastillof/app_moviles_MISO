package com.example.vynilos.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vynilos.models.Comment
import com.example.vynilos.repositories.CommentRepository

class CommentDetailViewModel {

    var liveData: MutableLiveData<Comment>
    private val commentRepository = CommentRepository()

    init {
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<Comment> {
        return liveData
    }

    fun makeApiCall(id: Number) {
        commentRepository.getComment(id, liveData)
    }

}

