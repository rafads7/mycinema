package com.rafaelduransaez.domain.mappers

import com.rafaelduransaez.domain.entities.Movie
import com.rafaelduransaez.domain.service_entities.RemoteMovie

fun RemoteMovie.toMovie() = Movie(id, title, posterPath)