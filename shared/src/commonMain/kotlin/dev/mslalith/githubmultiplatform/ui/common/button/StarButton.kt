package dev.mslalith.githubmultiplatform.ui.common.button

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.ui.bottomsheets.ImageVectorIcon
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_200
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_700

@Composable
fun StarButton(
    text: StringResource,
    textAllCaps: Boolean = false,
    icon: ImageVector? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    onClick: () -> Unit
) {
    val containerColor = if (darkTheme) Bg_Gray_Dark_700 else MaterialTheme.colorScheme.surface
    val contentColor = if (darkTheme) Bg_Gray_Dark_200 else MaterialTheme.colorScheme.onSurface

    ElevatedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        if (icon != null) {
            ImageVectorIcon(
                icon = icon,
                color = contentColor
            )
            HorizontalSpace(space = 12.dp)
        }
        Text(
            text = stringResource(resource = text)
                .let { if (textAllCaps) it.uppercase() else it },
            color = contentColor,
            fontSize = 14.sp
        )
    }
}
