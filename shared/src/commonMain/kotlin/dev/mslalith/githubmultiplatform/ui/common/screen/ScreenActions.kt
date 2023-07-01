package dev.mslalith.githubmultiplatform.ui.common.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab

interface ScreenActions {
    @Composable
    fun RowScope.Actions()
}

private object EmptyScreenActions : ScreenActions {
    @Composable
    override fun RowScope.Actions() = Unit
}

fun Screen.actions(): ScreenActions = (this as? ScreenActions) ?: EmptyScreenActions
fun Tab.actions(): ScreenActions = (this as? ScreenActions) ?: EmptyScreenActions
