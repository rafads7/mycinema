package com.rafaelduransaez.data.datasources

import com.rafaelduransaez.domain.entities.Movie

interface MoviesRemoteDataSource {
    suspend fun listPopularMovies(region: String): List<Movie>
}
