package dev.mslalith.githubmultiplatform.ui.bottomsheets

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter

@Composable
fun ImageVectorIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    color: Color,
    contentDescription: String = ""
) {
    Image(
        painter = rememberVectorPainter(image = icon),
        contentDescription = contentDescription,
        colorFilter = ColorFilter.tint(color = color),
        modifier = modifier
    )
}
