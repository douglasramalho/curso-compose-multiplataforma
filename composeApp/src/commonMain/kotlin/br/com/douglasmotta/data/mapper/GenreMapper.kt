package br.com.douglasmotta.data.mapper

import br.com.douglasmotta.data.network.model.GenreResponse
import br.com.douglasmotta.domain.model.Genre

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name,
)