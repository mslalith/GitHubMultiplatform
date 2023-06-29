package dev.mslalith.githubmultiplatform.extensions

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromColor(hexString: String): Color = hexString
    .removePrefix(prefix = "#")
    .lowercase()
    .let { if (it.length == 6) "FF$it" else it }
    .let { Color(it.toLong(radix = 16)) }
