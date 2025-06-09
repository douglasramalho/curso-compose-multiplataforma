package br.com.douglasmotta.ui.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoutes {
    @Serializable
    data object MoviesList : AppRoutes
    @Serializable
    data class MovieDetail(val movieId: Int) : AppRoutes
}