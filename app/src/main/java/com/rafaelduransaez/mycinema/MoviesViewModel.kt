package com.rafaelduransaez.mycinema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelduransaez.domain.entities.Movie
import com.rafaelduransaez.framework.api.RetrofitClient
import com.rafaelduransaez.mycinema.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(): ViewModel() {

    private var _state: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        listPopularMovies()
    }
    private fun listPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = RetrofitClient.service.popularMovies(Constants.api_key)
                .results.map { Movie(it.title, it.posterPath) }
            _state.value = UiState(
                loading = false, movies = movies
            )
        }
    }

}

data class UiState(
    var loading: Boolean = false,
    var error: Boolean = false,
    var movies: List<Movie> = emptyList()
)