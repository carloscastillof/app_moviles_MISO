package com.example.vynilos.models
import com.example.vynilos.repositories.CommentRepository

data class Comment (
    val id:Number?,
    val description: String,
    val raiting: Number,
    val albumId: Number,
    val collectorId: Number
){
    private val commentRepository = CommentRepository()
    fun jsonPostString() : String {
        return "{\"description\":\"${this.description}\",\"raiting\":${this.raiting},\"albumId\":${this.albumId},\"collectorId\":${this.collectorId}}"
    }

}