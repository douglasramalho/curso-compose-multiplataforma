package br.com.douglasmotta.di

import br.com.douglasmotta.ui.movieslist.MoviesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesListViewModel(get()) }
}