package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import compose.icons.Octicons
import compose.icons.octicons.Filter16
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.bottomsheets.ImageVectorIcon
import dev.mslalith.githubmultiplatform.ui.common.FilterItem
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenFilters
import dev.mslalith.githubmultiplatform.ui.common.screen.filters
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Blue
import dev.mslalith.githubmultiplatform.ui.theme.Text_White

@Composable
internal fun AnimatedScreenFilters() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedFilters(screenFilters = navigator.lastItem.filters())
}

@Composable
internal fun AnimatedTabFilters() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedFilters(screenFilters = tabNavigator.current.filters())
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedFilters(
    screenFilters: ScreenFilters
) {
    AnimatedContent(
        targetState = screenFilters,
        transitionSpec = { slideInVertically() + fadeIn() with slideOutVertically() + fadeOut() }
    ) {
        Row {
            HorizontalSpace(space = 24.dp)
            ClearActiveFilterCount(screenFilters = screenFilters)
            with(it) { Filters() }
            HorizontalSpace(space = 24.dp)
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ClearActiveFilterCount(
    screenFilters: ScreenFilters,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val (activeCount, clearFilters) = screenFilters.activeFilterCountAndClear()

    AnimatedContent(
        targetState = activeCount > 0
    ) {
        when (it) {
            false -> Unit
            true -> {
                val updatedClearFilters by rememberUpdatedState(newValue = clearFilters)
                val countBackgroundColor = if (darkTheme) Text_White else Bg_Blue
                val countContentColor = if (darkTheme) MaterialTheme.colorScheme.surface else Text_White

                FilterItem(
                    modifier = Modifier.padding(end = 12.dp),
                    selected = false,
                    text = SharedRes.strings._empty,
                    leading = {
                        ImageVectorIcon(
                            icon = Octicons.Filter16,
                            color = countBackgroundColor
                        )
                    },
                    trailing = {
                        Box(
                            modifier = Modifier
                                .size(size = FilterChipDefaults.Height * 0.6f)
                                .clip(shape = CircleShape)
                                .background(color = countBackgroundColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = activeCount.toString(),
                                color = countContentColor
                            )
                        }
                    },
                    onClick = updatedClearFilters
                )
            }
        }
    }
}
