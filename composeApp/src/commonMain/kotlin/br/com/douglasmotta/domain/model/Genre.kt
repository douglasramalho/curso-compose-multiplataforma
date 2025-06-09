package br.com.douglasmotta.domain.model

import br.com.douglasmotta.data.network.model.GenreResponse

data class Genre(
    val id: Int,
    val name: String,
)

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name,
)

// fake objects
val genre1 = Genre(
    id = 1,
    name = "Action",
)

val genre2 = Genre(
    id = 2,
    name = "Adventure",
)
