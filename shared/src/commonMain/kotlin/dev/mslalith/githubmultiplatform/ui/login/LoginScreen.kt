package dev.mslalith.githubmultiplatform.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import compose.icons.Octicons
import compose.icons.octicons.LogoGithub16

class LoginScreen : Screen {

    override val key: ScreenKey = toString()

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = rememberVectorPainter(Octicons.LogoGithub16),
                contentDescription = "GitHub Logo",
                modifier = Modifier.weight(weight = 1f)
            )
            ElevatedButton(
                onClick = {}
            ) {
                Text(text = "Sign In")
            }
        }
    }
}
