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
import br.com.douglasmotta.domain.model.MovieSection
import br.com.douglasmotta.ui.components.MoviesSection
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoviesListRoute(
    moviesListViewModel: MoviesListViewModel = koinViewModel(),
) {
    val moviesListState by moviesListViewModel.moviesListState.collectAsStateWithLifecycle()

    MoviesListScreen(
        moviesListState = moviesListState,
    )
}

@Composable
@Preview
fun MoviesListScreen(
    moviesListState: MoviesListViewModel.MoviesListState,
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
                                        title = "Popular Movies",
                                        movies = movieSection.movies
                                    )
                                }
                                MovieSection.SectionType.TOP_RATED -> {
                                    MoviesSection(
                                        title = "Top Rated Movies",
                                        movies = movieSection.movies
                                    )
                                }
                                MovieSection.SectionType.UPCOMING -> {
                                    MoviesSection(
                                        title = "Upcoming Movies",
                                        movies = movieSection.movies
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