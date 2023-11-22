package com.rafaelduransaez.usecases

import com.rafaelduransaez.data.repositories.MoviesRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend operator fun invoke() = moviesRepository.getPopularMovies()
}