package br.com.douglasmotta

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.douglasmotta.ui.components.MoviePoster
import br.com.douglasmotta.ui.movieslist.MoviesListScreen

@Preview(showBackground = true)
@Composable
fun MoviePosterPreview() {
    MoviePoster()
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesListScreen()
}