package br.com.douglasmotta.domain.model

data class Movie(
    val title: String,
    val posterUrl: String
)

// fake objects
val movie1 = Movie(
    title = "A Minecraft Movie",
    posterUrl = "url"
)
