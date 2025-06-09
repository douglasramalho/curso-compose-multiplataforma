package br.com.douglasmotta.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual fun Double.formatRating(): String {
    val formatter = NSNumberFormatter()
    formatter.minimumFractionDigits = 0u
    formatter.maximumFractionDigits = 2u
    formatter.numberStyle = 1u
    return formatter.stringFromNumber(NSNumber(this))!!
}