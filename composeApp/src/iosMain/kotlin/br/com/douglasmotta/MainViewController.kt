package br.com.douglasmotta

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController {
    CompositionLocalProvider(LocalUrlLauncher provides UrlLauncher()) {
        App()
    }
}