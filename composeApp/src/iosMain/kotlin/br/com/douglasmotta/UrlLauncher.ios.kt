package br.com.douglasmotta

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class UrlLauncher {
    actual fun openUrl(url: String) {
        UIApplication.sharedApplication.openURL(NSURL(url))
    }
}