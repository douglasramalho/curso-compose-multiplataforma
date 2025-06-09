package br.com.douglasmotta.data.mapper

import br.com.douglasmotta.data.network.model.VideoResponse
import br.com.douglasmotta.data.network.model.VideosListResponse
import br.com.douglasmotta.domain.model.MovieTrailer

private const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="

fun VideosListResponse.toModel(): MovieTrailer {
    if (this.results.isEmpty()) {
        throw IllegalStateException("No trailer found for this movie")
    }

    return this.results
        .firstOrNull { it.site == "YouTube" && it.type == "Trailer" && it.official }
        ?.toModel() ?: throw IllegalStateException("Could not find any suitable video for this movie")
}

private fun VideoResponse.toModel() = MovieTrailer(
    name = this.name,
    url = "$BASE_YOUTUBE_URL${this.key}",
)