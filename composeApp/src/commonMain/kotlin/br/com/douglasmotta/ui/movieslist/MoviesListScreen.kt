package br.com.douglasmotta.ui.movieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.douglasmotta.data.network.IMAGE_SMALL_BASE_URL
import br.com.douglasmotta.data.network.KtorApiClient
import br.com.douglasmotta.domain.model.Movie
import br.com.douglasmotta.domain.model.movie1
import br.com.douglasmotta.ui.components.MoviesSection
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviesListRoute() {

    var popularMovies by remember {
        mutableStateOf(emptyList<Movie>())
    }

    LaunchedEffect(Unit) {
        val response = KtorApiClient.getMovies("popular")
        popularMovies = response.results.map {
            Movie(
                id = it.id,
                title = it.title,
                overview = it.overview,
                posterUrl = "$IMAGE_SMALL_BASE_URL${it.posterPath}",
            )
        }
    }

    MoviesListScreen(
        popularMovies = popularMovies,
    )
}

@Composable
@Preview
fun MoviesListScreen(
    popularMovies: List<Movie>,
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                MoviesSection(
                    title = "Popular Movies",
                    movies = popularMovies,
                )
            }

            item {
                MoviesSection(
                    title = "Top Rated Movies",
                    movies = List(10) {
                        movie1
                    }
                )
            }

            item {
                MoviesSection(
                    title = "Upcoming Movies",
                    movies = List(10) {
                        movie1
                    }
                )
            }
        }
    }
}