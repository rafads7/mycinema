package com.rafaelduransaez.domain.entities

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val favorite: Boolean = false
)