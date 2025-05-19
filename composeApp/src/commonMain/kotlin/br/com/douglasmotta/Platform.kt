package br.com.douglasmotta

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform