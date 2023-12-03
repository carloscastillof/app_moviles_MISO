package com.example.vynilos.network

import com.example.vynilos.models.Album
import com.example.vynilos.models.Artist
import com.example.vynilos.models.Comment
import com.example.vynilos.models.Track
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getAlbums(@Url url:String):Call<List<Album>>

    @GET
    fun getAlbum(@Url url:String):Call<Album>

    @GET
    fun getArtist(@Url url:String):Call<Artist>

    @GET("/bands")
    fun getMusicians(): Call<List<Artist>>

    @GET
    fun getTrack(@Url url: String): Call<Track>

    @POST
    fun createAlbum(@Url url:String, @Body album: JsonObject): Call<Album>

    @POST

    fun createCommentToAlbum(@Url url:String, @Body comment: Comment): Call<Comment>

    @GET
    fun getComment(@Url url:String): Call<Comment>

    @POST
    fun createTrackToAlbum(@Url url:String, @Body track: Track): Call<Track>

    @POST
    fun createAlbumToBand(@Url url:String): Call<Album>
}
