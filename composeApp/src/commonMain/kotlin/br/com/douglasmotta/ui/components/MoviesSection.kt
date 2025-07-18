package br.com.douglasmotta.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.douglasmotta.domain.model.Movie

@Composable
fun MoviesSection(
    title: String,
    movies: List<Movie>,
    onMoviePosterClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = title,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge,
        )

        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(movies) { movie ->
                MoviePoster(
                    movie = movie,
                    onMoviePosterClick = onMoviePosterClick,
                )
            }
        }
    }
}