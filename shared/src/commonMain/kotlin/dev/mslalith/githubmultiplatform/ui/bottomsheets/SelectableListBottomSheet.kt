package dev.mslalith.githubmultiplatform.ui.bottomsheets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import compose.icons.Octicons
import compose.icons.octicons.Check24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.data.model.Selectable
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue

data class SelectableListBottomSheetItem<T>(
    override val value: T,
    val text: StringResource,
    val selected: Boolean
) : Selectable<T>() {

    override val isSelected: Boolean
        get() = selected

    override fun text(): StringResource = text
}

class SelectableListBottomSheet<T>(
    private val header: StringResource,
    private val items: List<SelectableListBottomSheetItem<T>>,
    private val onSelected: (T) -> Unit
) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        Column {
            VerticalSpace(space = 24.dp)
            Text(
                text = stringResource(resource = header),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            VerticalSpace(space = 24.dp)

            LazyColumn(
                contentPadding = PaddingValues(bottom = 12.dp)
            ) {
                items(
                    items = items
                ) {
                    SelectableListItem(
                        text = stringResource(resource = it.text()),
                        selected = it.isSelected,
                        onClick = {
                            onSelected(it.value)
                            bottomSheetNavigator.hide()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectableListItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 56.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalSpace(space = 24.dp)
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(weight = 1f),
            fontSize = 16.sp
        )
        AnimatedVisibility(visible = selected) {
            Image(
                painter = rememberVectorPainter(image = Octicons.Check24),
                contentDescription = "",
                colorFilter = ColorFilter.tint(color = Bg_Blue),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        HorizontalSpace(space = 24.dp)
    }
}
