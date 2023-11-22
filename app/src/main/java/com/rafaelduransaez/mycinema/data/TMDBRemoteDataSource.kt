package com.rafaelduransaez.mycinema.data

import com.rafaelduransaez.data.datasources.MoviesRemoteDataSource
import com.rafaelduransaez.domain.mappers.toMovie
import com.rafaelduransaez.mycinema.di.ApiKey
import com.rafaelduransaez.mycinema.framework.api.MoviesService
import javax.inject.Inject

class TMDBRemoteDataSource @Inject constructor(
    private val apiService: MoviesService,
    @ApiKey private val apiKey: String
) : MoviesRemoteDataSource {

    override suspend fun listPopularMovies(region: String) =
        apiService.popularMovies(apiKey, region)
            .results.map { it.toMovie() }
}