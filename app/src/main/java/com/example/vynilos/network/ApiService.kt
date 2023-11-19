package com.example.vynilos.network

import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getAlbums(@Url url:String):Call<List<Album>>

    @GET
    fun getAlbum(@Url url:String):Call<Album>

    @GET("/musicians")
    fun getMusicians(): Call<List<Artist>>
}
