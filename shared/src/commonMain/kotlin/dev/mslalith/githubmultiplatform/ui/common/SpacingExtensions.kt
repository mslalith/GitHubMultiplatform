package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import dev.mslalith.githubmultiplatform.ui.theme.borderLight

@Composable
fun HorizontalSpace(space: Dp) = Spacer(modifier = Modifier.width(width = space))

@Composable
fun VerticalSpace(space: Dp) = Spacer(modifier = Modifier.height(height = space))

@Composable
fun RowScope.FillSpace(weight: Float = 1f) = Spacer(modifier = Modifier.weight(weight = weight))

@Composable
fun ColumnScope.FillSpace(weight: Float = 1f) = Spacer(modifier = Modifier.weight(weight = weight))

@Composable
fun HorizontalLine(
    height: Dp,
    color: Color = MaterialTheme.colorScheme.borderLight
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = height)
            .background(color = color)
    )
}

@Composable
fun VerticalLine(
    width: Dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .width(width = width)
            .fillMaxHeight()
            .background(color = color)
    )
}
