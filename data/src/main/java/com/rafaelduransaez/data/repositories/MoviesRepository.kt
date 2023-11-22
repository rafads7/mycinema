package com.rafaelduransaez.data.repositories

import com.rafaelduransaez.data.datasources.MoviesRemoteDataSource
import com.rafaelduransaez.domain.entities.Movie
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val locationRepository: LocationRepository,
                                           private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun getPopularMovies(): List<Movie> {
        val region = locationRepository.getCurrentRegion()
        return moviesRemoteDataSource.listPopularMovies(region)
    }
}