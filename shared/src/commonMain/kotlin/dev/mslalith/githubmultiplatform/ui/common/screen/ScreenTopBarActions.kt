package dev.mslalith.githubmultiplatform.ui.common.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab

interface ScreenTopBarActions {
    @Composable
    fun RowScope.TopBarActions()
}

private object EmptyScreenActions : ScreenTopBarActions {
    @Composable
    override fun RowScope.TopBarActions() = Unit
}

fun Screen.topBarActions(): ScreenTopBarActions = (this as? ScreenTopBarActions) ?: EmptyScreenActions
fun Tab.topBarActions(): ScreenTopBarActions = (this as? ScreenTopBarActions) ?: EmptyScreenActions
