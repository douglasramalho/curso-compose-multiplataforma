package br.com.douglasmotta.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorApiClient {

    private val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNGVjY2NhMmY0ZjU5Yzg5ZjRlYTdlZDA2ZmQzODRkMSIsIm5iZiI6MTUyMzY0MDk5My4xNjMsInN1YiI6IjVhZDBlYWExMGUwYTI2MzAyZjAwZTM3MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.vD8PfoGdgYYXENJZRTzBKCchMpqKlr_xSCHxu-NRBFE", "")
                }
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    }
}