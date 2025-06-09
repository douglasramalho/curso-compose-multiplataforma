package br.com.douglasmotta.ui.moviedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import br.com.douglasmotta.data.repository.MoviesRepository
import br.com.douglasmotta.domain.model.Movie
import br.com.douglasmotta.ui.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val movieDetailRoute = savedStateHandle.toRoute<AppRoutes.MovieDetail>()

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)
    val movieDetailState = _movieDetailState.asStateFlow()

    private val _watchTrailerState = MutableStateFlow<WatchTrailerState?>(null)
    val watchTrailerState = _watchTrailerState.asStateFlow()

    init {
        getMovieDetail(movieDetailRoute.movieId)
    }

    private fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            moviesRepository.getMovieDetail(movieId).fold(
                onSuccess = { movie ->
                    _movieDetailState.update {
                        MovieDetailState.Success(movie)
                    }
                },
                onFailure = { error ->
                    _movieDetailState.update {
                        MovieDetailState.Error(error.message ?: "Unknown error")
                    }
                }
            )
        }
    }

    fun watchTrailer() {
        viewModelScope.launch {
            // Set loading state
            _watchTrailerState.update { WatchTrailerState.Loading }

            moviesRepository.getMovieTrailer(movieDetailRoute.movieId).fold(
                onSuccess = { movieTrailer ->
                    _watchTrailerState.update {
                        WatchTrailerState.Success(movieTrailer.url)
                    }
                },
                onFailure = { error ->
                    _watchTrailerState.update {
                        WatchTrailerState.Error(error.message ?: "Unknown error")
                    }
                }
            )
        }
    }

    fun resetWatchTrailerState() {
        _watchTrailerState.update { null }
    }

    sealed interface MovieDetailState {
        data object Loading : MovieDetailState
        data class Success(val movie: Movie) : MovieDetailState
        data class Error(val message: String) : MovieDetailState
    }

    sealed interface WatchTrailerState {
        data object Loading : WatchTrailerState
        data class Success(val youtubeUrl: String) : WatchTrailerState
        data class Error(val message: String) : WatchTrailerState
    }
}