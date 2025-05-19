package br.com.douglasmotta.ui.movieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.douglasmotta.domain.model.movie1
import br.com.douglasmotta.ui.components.MoviesSection
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoviesListRoute() {
    MoviesListScreen()
}

@Composable
@Preview
fun MoviesListScreen() {
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
                    movies = List(10) {
                        movie1
                    }
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