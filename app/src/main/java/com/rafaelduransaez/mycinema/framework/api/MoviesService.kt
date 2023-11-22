package com.rafaelduransaez.mycinema.framework.api

import com.rafaelduransaez.domain.service_entities.RemoteMoviesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun popularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String? = null
    ): RemoteMoviesResult
}
