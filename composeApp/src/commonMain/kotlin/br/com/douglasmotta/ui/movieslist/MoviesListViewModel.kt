package br.com.douglasmotta.ui.movieslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.douglasmotta.data.repository.MoviesRepository
import br.com.douglasmotta.domain.model.MovieSection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesListViewModel(
    private val moviesRepository: MoviesRepository,
) : ViewModel() {

    private val _movieSections = MutableStateFlow<MoviesListState>(MoviesListState.Loading)
    val movieSections = _movieSections.asStateFlow()

    init {
        getMovieSections()
    }

    private fun getMovieSections() {
        viewModelScope.launch {
            try {
                _movieSections.update {
                    MoviesListState.Success(moviesRepository.getMovieSections())
                }
            } catch (e: Exception) {
                MoviesListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    sealed interface MoviesListState {
        data object Loading : MoviesListState
        data class Success(val movieSections: List<MovieSection>) : MoviesListState
        data class Error(val message: String) : MoviesListState
    }
}