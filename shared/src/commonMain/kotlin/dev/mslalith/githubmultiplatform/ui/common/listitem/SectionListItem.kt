package dev.mslalith.githubmultiplatform.ui.common.listitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.theme.Text_White

@Composable
internal fun SectionListItem(
    sectionItemType: SectionItemType,
    trailing: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onClick() }
    ) {
        HorizontalSpace(space = 24.dp)
        RoundedSquareIcon(
            icon = sectionItemType.icon,
            backgroundColor = sectionItemType.iconBackgroundColor
        )
        HorizontalSpace(space = 24.dp)
        Text(
            text = stringResource(resource = sectionItemType.stringResource),
            modifier = Modifier.weight(weight = 1f)
        )
        if (trailing != null) {
            HorizontalSpace(space = 12.dp)
            trailing()
        }
        HorizontalSpace(space = 24.dp)
    }
}

@Composable
private fun RoundedSquareIcon(
    icon: ImageVector,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 4.dp)
            )
            .padding(all = 8.dp)
    ) {
        Image(
            painter = rememberVectorPainter(image = icon),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Text_White),
        )
    }
}
