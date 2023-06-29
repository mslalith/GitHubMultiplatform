package dev.mslalith.githubmultiplatform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GitHubMultiplatformTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color(0xFFBB86FC),
            primaryContainer = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            background = Bg_Grey_Dark,
            surface = Bg_Grey_Dark,
            onBackground = Text_White,
            onSurface = Text_White
        )
    } else {
        lightColorScheme(
            primary = Color(0xFF6200EE),
            primaryContainer = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            background = Bg_Gray_Light,
            surface = Bg_Gray_Light,
            onBackground = Text_Gray_Dark,
            onSurface = Text_Gray_Dark
        )
    }
    val typography = Typography()
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
