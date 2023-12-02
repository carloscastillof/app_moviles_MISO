package com.example.vynilos.models

import com.google.gson.annotations.SerializedName

data class Collector(
    @SerializedName("id") val id: Int
)
data class Comment(
    @SerializedName("description") val description: String,
    @SerializedName("rating") val rating: Number,
    @SerializedName("collector") val collector: Collector = Collector(id = 1)
){
    constructor(): this("null",0)
}