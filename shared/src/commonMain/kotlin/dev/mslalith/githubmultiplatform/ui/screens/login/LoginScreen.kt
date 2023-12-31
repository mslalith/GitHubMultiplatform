package dev.mslalith.githubmultiplatform.ui.screens.login

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.Octicons
import compose.icons.octicons.MarkGithub16
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.bottomsheets.ImageVectorIcon
import dev.mslalith.githubmultiplatform.ui.common.navigator.LocalAppNavigator
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.Login
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.LoginInProgress
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.NavigateToMain
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.Splash
import dev.mslalith.githubmultiplatform.ui.screens.main.MainScreen
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_900
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Light
import dev.mslalith.githubmultiplatform.utils.deeplink.DeepLinkHandler
import kotlinx.coroutines.delay

private const val SPLASH_TIMEOUT = 3000L

object LoginScreen : Screen {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { LoginScreenModel() }
        val state by screenModel.state.collectAsState()

        val uriHandler = LocalUriHandler.current
        val navigator = LocalAppNavigator.currentOrThrow

        DeepLinkHandler { screenModel.handleDeepLink(deepLink = it) }

        LaunchedEffect(key1 = Unit) {
            if (state is Splash) {
                delay(timeMillis = SPLASH_TIMEOUT)
                screenModel.checkLogin()
            }
        }

        LaunchedEffect(key1 = state) {
            if (state is NavigateToMain) navigator.replace(item = MainScreen())
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxWidth(fraction = 0.3f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                ImageVectorIcon(
                    modifier = Modifier.matchParentSize(),
                    icon = Octicons.MarkGithub16,
                    color = Bg_Gray_Light
                )
            }

            Column {
                Spacer(modifier = Modifier.weight(weight = 1f))

                AnimatedContent(
                    targetState = state,
                    transitionSpec = {
                        slideInVertically { it } + fadeIn() with slideOutVertically { it } + fadeOut()
                    },
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    when (it) {
                        Login -> LoginUi { uriHandler.openUri(uri = screenModel.getAuthUrl()) }
                        LoginInProgress -> LoginInProgressUi()
                        else -> Unit
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginUi(
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = Bg_Gray_Light
        )
    ) {
        Text(
            text = stringResource(resource = SharedRes.strings.sign_in_with_github).uppercase(),
            color = Bg_Gray_Dark_900
        )
    }
}

@Composable
private fun LoginInProgressUi() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(size = 36.dp))
    }
}
