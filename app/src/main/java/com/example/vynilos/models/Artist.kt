package com.example.vynilos.models

import java.text.SimpleDateFormat

data class Artist (
    val id: Number,
    val name: String,
    val image: String,
    val description: String,
    val creationDate: String,
        ) {
    fun parsedCreationDate(): String {
        var parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(this.creationDate)
        var formatter = SimpleDateFormat("dd/MMM/yyyy")

        return formatter.format(parser)
    }
}