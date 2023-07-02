package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import compose.icons.Octicons
import compose.icons.octicons.ChevronDown16
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.ui.filters.FilterStateHolder
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue
import dev.mslalith.githubmultiplatform.ui.theme.Text_White
import dev.mslalith.githubmultiplatform.ui.theme.borderLight

@Composable
fun <T> FilterItem(
    filterStateHolder: FilterStateHolder<T>,
    trailingIcon: ImageVector = Octicons.ChevronDown16,
    onClick: () -> Unit
) {
    FilterItem(
        selected = !filterStateHolder.isInitial,
        text = filterStateHolder.mapToStringResource(value = filterStateHolder.selectedType),
        trailingIcon = trailingIcon,
        onClick = onClick
    )
}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    text: StringResource,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    FilterItem(
        modifier = modifier,
        selected = selected,
        text = text,
        leading = if (leadingIcon != null) {
            @Composable {
                Image(
                    painter = rememberVectorPainter(image = leadingIcon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = LocalContentColor.current)
                )
            }
        } else null,
        trailing = if (trailingIcon != null) {
            @Composable {
                Image(
                    painter = rememberVectorPainter(image = trailingIcon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = LocalContentColor.current)
                )
            }
        } else null,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    text: StringResource,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = selected,
        onClick = onClick,
        leadingIcon = leading,
        trailingIcon = trailing,
        label = { Text(text = stringResource(resource = text)) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.borderLight,
            labelColor = MaterialTheme.colorScheme.onSurface,
            iconColor = MaterialTheme.colorScheme.onSurface,
            selectedContainerColor = Bg_Blue,
            selectedLabelColor = Text_White,
            selectedLeadingIconColor = Text_White,
            selectedTrailingIconColor = Text_White,
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent,
            selectedBorderColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = 24.dp)
    )
}
