package dev.mslalith.githubmultiplatform.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import compose.icons.Octicons
import compose.icons.octicons.MarkGithub16
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.deeplink.DeepLinkHandler
import dev.mslalith.githubmultiplatform.ui.main.MainScreen
import dev.mslalith.githubmultiplatform.ui.theme.Dark_DarkGreyText
import dev.mslalith.githubmultiplatform.ui.theme.Dark_GrayLight
import kotlinx.coroutines.delay

private const val SPLASH_TIMEOUT = 3000L

object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { LoginScreenModel() }
        val state by screenModel.state.collectAsState()

        val uriHandler = LocalUriHandler.current
        val navigator = LocalNavigator.current

        val showLoginUi by remember {
            derivedStateOf { state is LoginScreenModel.State.Login }
        }

        DeepLinkHandler { screenModel.handleDeepLink(deepLink = it) }

        LaunchedEffect(key1 = Unit) {
            if (state is LoginScreenModel.State.Splash) {
                delay(timeMillis = SPLASH_TIMEOUT)
                screenModel.checkLogin()
            }
        }

        LaunchedEffect(key1 = state) {
            if (state is LoginScreenModel.State.NavigateToMain) navigator?.replace(item = MainScreen())
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
                Image(
                    painter = rememberVectorPainter(Octicons.MarkGithub16),
                    contentDescription = "GitHub Logo",
                    colorFilter = ColorFilter.tint(color = Dark_GrayLight),
                    modifier = Modifier.matchParentSize()
                )
            }

            Column {
                Spacer(modifier = Modifier.weight(weight = 1f))

                AnimatedVisibility(
                    visible = showLoginUi,
                    enter = slideInVertically { it } + fadeIn(),
                    exit = slideOutVertically { it } + fadeOut()
                ) {
                    ElevatedButton(
                        onClick = { uriHandler.openUri(uri = screenModel.getAuthUrl()) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 12.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = Dark_GrayLight
                        )
                    ) {
                        Text(
                            text = stringResource(resource = SharedRes.strings.sign_in_with_github).uppercase(),
                            color = Dark_DarkGreyText
                        )
                    }
                }
            }
        }
    }
}
