package br.com.douglasmotta.domain.model

import br.com.douglasmotta.data.network.model.MovieResponse

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
)

fun MovieResponse.toModel() = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = this.posterPath,
)

// fake objects
val movie1 = Movie(
    id = 1,
    title = "A Minecraft Movie",
    overview = "Movie overview",
    posterUrl = "url",
)
