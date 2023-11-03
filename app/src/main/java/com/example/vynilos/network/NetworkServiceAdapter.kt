package com.example.vynilos.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceAdapter {

    companion object {
<<<<<<< HEAD
        val BASE_URL = "http://34.69.228.4:3000"
=======
        val BASE_URL = "http://34.69.228.4:3000s"
>>>>>>> main

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build()
        }
    }
}