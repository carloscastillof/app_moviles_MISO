package com.example.vynilos.models

import com.google.gson.annotations.SerializedName

data class Track (
    @SerializedName("name") val name: String,
    @SerializedName("duration") val duration: String,

    ){
    constructor(): this("null","null")
}