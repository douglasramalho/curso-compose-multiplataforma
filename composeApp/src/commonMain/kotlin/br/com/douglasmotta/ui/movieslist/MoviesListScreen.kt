package br.com.douglasmotta.ui.movieslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.douglasmotta.ui.components.MoviePoster
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
        ) {
            item {
                Text(
                    text = "Popular Movies",
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            
            item {
                LazyRow(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(10) {
                        MoviePoster()
                    }
                }
            }
        }
    }
}