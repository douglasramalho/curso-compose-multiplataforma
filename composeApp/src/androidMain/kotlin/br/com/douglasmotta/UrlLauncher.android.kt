package br.com.douglasmotta

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

actual class UrlLauncher(private val context: Context) {
    actual fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }
}