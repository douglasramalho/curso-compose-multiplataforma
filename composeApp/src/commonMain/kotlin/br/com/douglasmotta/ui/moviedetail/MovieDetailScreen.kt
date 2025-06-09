package br.com.douglasmotta.ui.moviedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.douglasmotta.LocalUrlLauncher
import br.com.douglasmotta.domain.model.Movie
import br.com.douglasmotta.ui.components.CastMemberItem
import br.com.douglasmotta.ui.components.MovieGenreChip
import br.com.douglasmotta.ui.components.MovieInfoItem
import coil3.compose.AsyncImage
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.Clock
import compose.icons.fontawesomeicons.solid.Play
import compose.icons.fontawesomeicons.solid.Star
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    navigateBack: () -> Unit,
) {
    val movieDetailState by viewModel.movieDetailState.collectAsStateWithLifecycle()
    val watchTrailerState by viewModel.watchTrailerState.collectAsStateWithLifecycle()

    if (watchTrailerState is MovieDetailViewModel.WatchTrailerState.Success && watchTrailerState != null) {
        LocalUrlLauncher.current.openUrl((watchTrailerState as MovieDetailViewModel.WatchTrailerState.Success).youtubeUrl)
        viewModel.resetWatchTrailerState()
    }

    if (watchTrailerState is MovieDetailViewModel.WatchTrailerState.Error) {
        AlertDialog(
            onDismissRequest = {
                viewModel.resetWatchTrailerState()
            },
            confirmButton = {
                Button(onClick = { viewModel.resetWatchTrailerState() }) {
                    Text("OK")
                }
            },
            title = {
                Text(
                    text = "Ops.."
                )
            },
            text = {
                Text(
                    text = "Trailer não disponível"
                )
            }
        )
    }

    MovieDetailScreen(
        movieDetailState = movieDetailState,
        watchTrailerState = watchTrailerState,
        onNavigationIconClick = navigateBack,
        onWatchTrailerClick = viewModel::watchTrailer
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailState: MovieDetailViewModel.MovieDetailState,
    watchTrailerState: MovieDetailViewModel.WatchTrailerState?,
    onNavigationIconClick: () -> Unit,
    onWatchTrailerClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Movie Detail")
                },
                navigationIcon = {
                    Surface(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        shape = MaterialTheme.shapes.small,
                    ) {
                        IconButton(
                            onClick = onNavigationIconClick,
                            modifier = Modifier
                                .size(32.dp)
                        ) {
                            Icon(
                                imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (movieDetailState) {
                MovieDetailViewModel.MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }
                is MovieDetailViewModel.MovieDetailState.Success -> {
                    MovieDetailContent(
                        movie = movieDetailState.movie,
                        watchTrailerState = watchTrailerState,
                        onWatchTrailerClick = onWatchTrailerClick,
                    )
                }
                is MovieDetailViewModel.MovieDetailState.Error -> {
                    Text(
                        text = movieDetailState.message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movie: Movie,
    watchTrailerState: MovieDetailViewModel.WatchTrailerState?,
    modifier: Modifier = Modifier,
    onWatchTrailerClick: () -> Unit,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
    ) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            shape = MaterialTheme.shapes.large,
        ) {
            AsyncImage(
                model = movie.posterUrl,
                contentDescription = null,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Star,
                    text = movie.rating
                )

                Spacer(modifier = Modifier.width(16.dp))

                movie.duration?.let {
                    MovieInfoItem(
                        icon = FontAwesomeIcons.Solid.Clock,
                        text = movie.duration
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                }

                MovieInfoItem(
                    icon = FontAwesomeIcons.Solid.Calendar,
                    text = "${movie.year}"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            movie.genres?.let { genres ->
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    genres.forEach { genre ->
                        MovieGenreChip(
                            genre = genre.name
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            ElevatedButton(
                onClick = onWatchTrailerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.Play,
                    contentDescription = null,
                    modifier = Modifier
                        .size(12.dp)
                )

                Text(
                    text = "Watch trailer",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.bodyMedium,
                )

                if (watchTrailerState is MovieDetailViewModel.WatchTrailerState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(22.dp)
                            .padding(start = 16.dp)
                    )
                }
            }

            movie.castMembers?.let { castMembers ->
                Spacer(modifier = Modifier.height(16.dp))

                BoxWithConstraints {
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(castMembers) { castMember ->
                            CastMemberItem(
                                profilePictureUrl = castMember.profileUrl,
                                name = castMember.name,
                                character = castMember.character,
                                modifier = Modifier
                                    .width(itemWidth)
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}