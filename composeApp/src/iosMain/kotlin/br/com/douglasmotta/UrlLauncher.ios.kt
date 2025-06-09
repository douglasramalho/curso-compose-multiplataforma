package br.com.douglasmotta

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class UrlLauncher {
    actual fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(
                url = nsUrl,
                options = emptyMap<Any?, Any>(),
                completionHandler = null
            )
        }
    }
}