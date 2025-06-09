package br.com.douglasmotta.ui.movieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.douglasmotta.domain.model.Movie
import br.com.douglasmotta.domain.model.MovieSection
import br.com.douglasmotta.ui.components.MoviesSection
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movies_list_popular_movies
import movies.composeapp.generated.resources.movies_list_top_rated_movies
import movies.composeapp.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoviesListRoute(
    moviesListViewModel: MoviesListViewModel = koinViewModel(),
    navigateToMovieDetail: (Movie) -> Unit,
) {
    val moviesListState by moviesListViewModel.moviesListState.collectAsStateWithLifecycle()

    MoviesListScreen(
        moviesListState = moviesListState,
        onMovieClick = navigateToMovieDetail
    )
}

@Composable
@Preview
fun MoviesListScreen(
    moviesListState: MoviesListViewModel.MoviesListState,
    onMovieClick: (Movie) -> Unit,
) {
    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when (moviesListState) {
                is MoviesListViewModel.MoviesListState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        items(moviesListState.movieSections) { movieSection ->
                            when (movieSection.section) {
                                MovieSection.SectionType.POPULAR -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_popular_movies),
                                        movies = movieSection.movies,
                                        onMoviePosterClick = onMovieClick
                                    )
                                }
                                MovieSection.SectionType.TOP_RATED -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_top_rated_movies),
                                        movies = movieSection.movies,
                                        onMoviePosterClick = onMovieClick
                                    )
                                }
                                MovieSection.SectionType.UPCOMING -> {
                                    MoviesSection(
                                        title = stringResource(Res.string.movies_list_upcoming_movies),
                                        movies = movieSection.movies,
                                        onMoviePosterClick = onMovieClick
                                    )
                                }
                            }
                        }
                    }
                }

                is MoviesListViewModel.MoviesListState.Error -> {
                    Text(
                        text = moviesListState.message,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                MoviesListViewModel.MoviesListState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}