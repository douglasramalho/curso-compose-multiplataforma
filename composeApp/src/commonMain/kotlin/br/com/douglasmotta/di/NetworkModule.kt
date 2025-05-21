package br.com.douglasmotta.di

import br.com.douglasmotta.data.network.KtorApiClient
import org.koin.dsl.module

val networkModule = module {
    single { KtorApiClient() }
}