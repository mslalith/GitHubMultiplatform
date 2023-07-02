package dev.mslalith.githubmultiplatform.extensions

fun Double.format(fractionDigits: Int): String {
    val digits = toString()
        .takeWhile { it != '.' }
    val decimals = toString()
        .dropWhile { it != '.' }
        .drop(n = 1)
        .take(n = fractionDigits)

    return if (decimals.toInt() == 0) digits else "$digits.$decimals"
}
