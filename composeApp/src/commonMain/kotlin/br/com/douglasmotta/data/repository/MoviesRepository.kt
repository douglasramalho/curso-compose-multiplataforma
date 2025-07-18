package br.com.douglasmotta.data.repository

import br.com.douglasmotta.data.mapper.toModel
import br.com.douglasmotta.data.network.KtorApiClient
import br.com.douglasmotta.domain.model.ImageSize
import br.com.douglasmotta.domain.model.Movie
import br.com.douglasmotta.domain.model.MovieSection
import br.com.douglasmotta.domain.model.MovieTrailer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val ktorApiClient: KtorApiClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend fun getMovieSections(): List<MovieSection> {
        return withContext(ioDispatcher) {
            val popularMoviesDeferred = async { ktorApiClient.getMovies("popular") }
            val topRatedMoviesDeferred = async { ktorApiClient.getMovies("top_rated") }
            val upcomingMoviesDeferred = async { ktorApiClient.getMovies("upcoming") }

            val popularMovies = popularMoviesDeferred.await()
            val topRatedMovies = topRatedMoviesDeferred.await()
            val upcomingMovies = upcomingMoviesDeferred.await()

            listOf(
                MovieSection(
                    section = MovieSection.SectionType.POPULAR,
                    movies = popularMovies.results.map { it.toModel() }
                ),
                MovieSection(
                    section = MovieSection.SectionType.TOP_RATED,
                    movies = topRatedMovies.results.map { it.toModel() }
                ),
                MovieSection(
                    section = MovieSection.SectionType.UPCOMING,
                    movies = upcomingMovies.results.map { it.toModel() }
                )
            )
        }
    }

    suspend fun getMovieDetail(movieId: Int): Result<Movie> {
        return withContext(ioDispatcher) {
            runCatching {
                val movieDetailDeferred = async { ktorApiClient.getMovieDetail(movieId) }
                val creditsDeferred = async { ktorApiClient.getCredits(movieId) }

                val movieDetailResponse = movieDetailDeferred.await()
                val creditsResponse = creditsDeferred.await()

                movieDetailResponse.toModel(
                    castMembersResponse = creditsResponse.cast,
                    imageSize = ImageSize.X_LARGE,
                )
            }
        }
    }

    suspend fun getMovieTrailer(movieId: Int): Result<MovieTrailer> {
        return withContext(ioDispatcher) {
            runCatching {
                val videosResponse = ktorApiClient.getVideos(movieId)
                videosResponse.toModel()
            }
        }
    }
}