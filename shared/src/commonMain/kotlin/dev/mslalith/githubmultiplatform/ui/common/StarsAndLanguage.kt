package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.StarFill16
import dev.mslalith.githubmultiplatform.data.model.RepositoryLanguage
import dev.mslalith.githubmultiplatform.extensions.format
import dev.mslalith.githubmultiplatform.extensions.fromColor
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_500
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Yellow

@Composable
fun StarsAndLanguage(
    stars: Int,
    language: RepositoryLanguage?
) {
    val formattedStars = remember(key1 = stars) {
        when {
            stars > 999 -> (stars / 1000.0).let { "${it.format(fractionDigits = 1)}k" }
            else -> stars.toString()
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberVectorPainter(image = Octicons.StarFill16),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Bg_Yellow)
        )
        HorizontalSpace(space = 8.dp)
        Text(
            text = formattedStars,
            color = Bg_Gray_Dark_500
        )
        if (language != null) {
            HorizontalSpace(space = 12.dp)
            Dot(
                size = 12.dp,
                color = Color.fromColor(hexString = language.color)
            )
            HorizontalSpace(space = 8.dp)
            Text(
                text = language.name,
                color = Bg_Gray_Dark_500
            )
        }
    }
}
