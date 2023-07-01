package dev.mslalith.githubmultiplatform.ui.common.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.with
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.common.screen.screenTitle

@Composable
internal fun AnimatedScreenTitle() {
    val navigator = LocalAppNavigator.currentOrThrow
    AnimatedTitle(title = navigator.lastItem.screenTitle())
}

@Composable
internal fun AnimatedTabTitle() {
    val tabNavigator = LocalTabNavigator.current
    AnimatedTitle(title = tabNavigator.current.screenTitle())
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedTitle(title: StringResource) {
    AnimatedContent(
        targetState = title,
        transitionSpec = { expandHorizontally() + fadeIn() with shrinkHorizontally() + fadeOut() }
    ) {
        Text(
            text = stringResource(resource = it),
            fontWeight = FontWeight.SemiBold
        )
    }
}
