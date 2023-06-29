package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue

@Composable
fun RoundIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String = "",
    enabled: Boolean = true,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = Bg_Blue,
    onClick: () -> Unit
) {
    IconButton(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .clip(shape = CircleShape)
            .background(color = backgroundColor)
    ) {
        Image(
            painter = rememberVectorPainter(image = icon),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(color = contentColor)
        )
    }
}
