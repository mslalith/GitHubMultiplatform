package dev.mslalith.githubmultiplatform.ui.common.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab

interface ScreenFilters {
    @Composable
    fun RowScope.Filters()
}

private object EmptyScreenFilters : ScreenFilters {
    @Composable
    override fun RowScope.Filters() = Unit
}

fun Screen.filters(): ScreenFilters = (this as? ScreenFilters) ?: EmptyScreenFilters
fun Tab.filters(): ScreenFilters = (this as? ScreenFilters) ?: EmptyScreenFilters
