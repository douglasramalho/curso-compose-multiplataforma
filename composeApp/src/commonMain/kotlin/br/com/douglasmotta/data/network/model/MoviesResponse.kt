package br.com.douglasmotta.data.network.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListResponse(
    val results: List<MovieResponse>
)

@Serializable
data class MovieResponse(
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String,
    val title: String,
    val overview: String,
    val genreResponses: List<GenreResponse>? = null,
    @SerialName("release_date")
    val releaseDate: LocalDate,
    val runtime: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double,
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String,
)
