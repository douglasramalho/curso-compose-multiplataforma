package br.com.douglasmotta

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val view = LocalView.current
            val window = (view.context as? Activity)?.window ?: return@setContent
            SideEffect {
                WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = false
            }
            
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}