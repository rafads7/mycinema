package com.rafaelduransaez.mycinema.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelduransaez.domain.entities.Movie
import com.rafaelduransaez.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {

    private var _state: MutableStateFlow<UiState> = MutableStateFlow(UiState(loading = true))
    val state: StateFlow<UiState> = _state.asStateFlow()

    private fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getPopularMoviesUseCase()
            _state.value = UiState(
                loading = false, movies = movies
            )
        }
    }

    fun onLocationPermissionRequested() {
        getPopularMovies()
    }

}

data class UiState(
    var loading: Boolean = false,
    var error: Boolean = false,
    var movies: List<Movie> = emptyList()
)