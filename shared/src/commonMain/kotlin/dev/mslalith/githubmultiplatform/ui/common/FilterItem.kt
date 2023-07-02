package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import dev.mslalith.githubmultiplatform.ui.theme.borderLight

@Composable
fun FilterItem(
    filterStateHolder: FilterStateHolder<StringResource>,
    trailingIcon: ImageVector = Octicons.ChevronDown16,
    onClick: () -> Unit
) {
    FilterItem(
        selected = !filterStateHolder.isInitial,
        text = filterStateHolder.selectedType,
        trailingIcon = trailingIcon,
        onClick = onClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterItem(
    selected: Boolean,
    text: StringResource,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        leadingIcon = if (leadingIcon != null) {
            @Composable {
                Image(
                    painter = rememberVectorPainter(image = leadingIcon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                )
            }
        } else null,
        trailingIcon = if (trailingIcon != null) {
            @Composable {
                Image(
                    painter = rememberVectorPainter(image = trailingIcon),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                )
            }
        } else null,
        label = {
            Text(
                text = stringResource(resource = text)
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.borderLight,
            labelColor = MaterialTheme.colorScheme.onBackground,
            iconColor = MaterialTheme.colorScheme.onBackground,
            selectedContainerColor = Bg_Blue
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.Transparent,
            selectedBorderColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = 24.dp)
    )
}
