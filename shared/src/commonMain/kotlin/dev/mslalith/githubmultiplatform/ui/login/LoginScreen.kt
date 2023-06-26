package dev.mslalith.githubmultiplatform.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import compose.icons.Octicons
import compose.icons.octicons.MarkGithub16
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.ui.main.MainScreen
import dev.mslalith.githubmultiplatform.ui.theme.Dark_DarkGreyText
import dev.mslalith.githubmultiplatform.ui.theme.Dark_GrayLight
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class LoginScreen : Screen {

    override val key: ScreenKey = toString()

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { LoginScreenModel() }

        val uriHandler = LocalUriHandler.current
        val navigator = LocalNavigator.current

        LaunchedEffect(key1 = screenModel) {
            screenModel.state.map { it.isLoggedIn }.distinctUntilChanged().collectLatest {
                if (it) navigator?.replace(item = MainScreen())
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberVectorPainter(Octicons.MarkGithub16),
                contentDescription = "GitHub Logo",
                colorFilter = ColorFilter.tint(color = Dark_GrayLight),
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth(fraction = 0.3f)
                    .align(alignment = Alignment.CenterHorizontally)
            )
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
