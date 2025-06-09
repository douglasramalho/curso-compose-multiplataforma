package br.com.douglasmotta

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.douglasmotta.domain.model.MovieSection
import br.com.douglasmotta.domain.model.castMember1
import br.com.douglasmotta.domain.model.castMember2
import br.com.douglasmotta.domain.model.movie1
import br.com.douglasmotta.ui.components.CastMemberItem
import br.com.douglasmotta.ui.components.MovieGenreChip
import br.com.douglasmotta.ui.components.MovieInfoItem
import br.com.douglasmotta.ui.components.MoviePoster
import br.com.douglasmotta.ui.moviedetail.MovieDetailScreen
import br.com.douglasmotta.ui.moviedetail.MovieDetailViewModel
import br.com.douglasmotta.ui.movieslist.MoviesListScreen
import br.com.douglasmotta.ui.movieslist.MoviesListViewModel
import br.com.douglasmotta.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Star

@Preview(showBackground = true)
@Composable
fun MoviePosterPreview() {
    MoviePoster(
        movie = movie1,
        onMoviePosterClick = {},
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
        ),
        onMovieClick = {},
    )
}

@Preview(showBackground = true)
@Composable
fun MoviesDetailScreenPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(movie1),
            watchTrailerState = null,
            onNavigationIconClick = {},
            onWatchTrailerClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieInfoItemPreview() {
    MoviesAppTheme {
        MovieInfoItem(
            icon = FontAwesomeIcons.Solid.Star,
            text = "7.5"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CastMemberItemPreview() {
    MoviesAppTheme {
        CastMemberItem(
            profilePictureUrl = "",
            name = "Will Smith",
            character = "Christopher Gardner",
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieGenreChipPreview() {
    MoviesAppTheme {
        MovieGenreChip(
            genre = "Drama"
        )
    }
}