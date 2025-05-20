package br.com.douglasmotta.data.network.model

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
)
