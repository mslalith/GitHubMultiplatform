package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Dot(
    size: Dp,
    color: Color,
    horizontalSpace: Dp = 0.dp,
    verticalSpace: Dp = 0.dp
) {
    Box(
        modifier = Modifier
            .padding(horizontal = horizontalSpace, vertical = verticalSpace)
            .size(size = size)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}
