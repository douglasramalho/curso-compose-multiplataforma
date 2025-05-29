package br.com.douglasmotta

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.douglasmotta.di.dataModule
import br.com.douglasmotta.di.networkModule
import br.com.douglasmotta.di.viewModelModule
import br.com.douglasmotta.ui.movieslist.MoviesListRoute
import br.com.douglasmotta.ui.navigation.AppRoutes
import br.com.douglasmotta.ui.theme.MoviesAppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            modules(networkModule, dataModule, viewModelModule)
        }
    ) {
        MoviesAppTheme {
            val navController = rememberNavController()
            NavHost(navController, startDestination = AppRoutes.MoviesList) {
                composable<AppRoutes.MoviesList> {
                    MoviesListRoute()
                }

                composable<AppRoutes.MovieDetail> {
                    // MovieDetailRoute()
                }
            }
        }
    }
}