package br.com.douglasmotta.di

import br.com.douglasmotta.data.repository.MoviesRepository
import org.koin.dsl.module

val dataModule = module {
    factory { MoviesRepository(get()) }
}