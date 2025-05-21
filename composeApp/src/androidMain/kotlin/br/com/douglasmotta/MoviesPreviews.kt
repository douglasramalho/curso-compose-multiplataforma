package br.com.douglasmotta

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.douglasmotta.domain.model.MovieSection
import br.com.douglasmotta.domain.model.movie1
import br.com.douglasmotta.ui.components.MoviePoster
import br.com.douglasmotta.ui.movieslist.MoviesListScreen
import br.com.douglasmotta.ui.movieslist.MoviesListViewModel

@Preview(showBackground = true)
@Composable
fun MoviePosterPreview() {
    MoviePoster(
        movie = movie1
    )
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesListScreen(
        moviesListState = MoviesListViewModel.MoviesListState.Success(
            listOf(
                MovieSection(
                    section = MovieSection.SectionType.POPULAR,
                    movies = listOf(movie1)
                )
            )
        )
    )
}