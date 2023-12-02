package com.example.vynilos.models

import com.example.vynilos.repositories.AlbumRepository
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Call

data class Album (
    val id:Number?,
    @SerializedName("name") val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    val tracks: Array<Track>,
    val comments: Array<Comment>
){

    private val albumsRepository = AlbumRepository()
    fun jsonPostString() : JsonObject {
        val paramObject = JsonObject()
        paramObject.addProperty("name", this.name)
        paramObject.addProperty("cover", this.cover)
        paramObject.addProperty("description", this.description)
        paramObject.addProperty("releaseDate", this.releaseDate)
        paramObject.addProperty("genre", this.genre)
        paramObject.addProperty("recordLabel", this.recordLabel)
        return paramObject
    }

    fun create(): Call<Album> {
        return albumsRepository.createAlbum(this)
    }
}