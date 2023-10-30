package com.example.vynilos.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceAdapter {

    companion object {
        val BASE_URL = "https://vynils-back-heroku.herokuapp.com/"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build()
        }
    }
}