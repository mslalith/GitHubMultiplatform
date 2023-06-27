package dev.mslalith.githubmultiplatform.ui.login

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClient
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.ui.login.LoginScreenModel.State.Login
import dev.mslalith.githubmultiplatform.ui.login.LoginScreenModel.State.NavigateToMain
import dev.mslalith.githubmultiplatform.ui.login.LoginScreenModel.State.Splash
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : StateScreenModel<LoginScreenModel.State>(initialState = Splash), KoinComponent {

    sealed class State {
        object Splash : State()
        object Login : State()
        object NavigateToMain : State()
    }

    private val authClient by inject<AuthClient>()
    private val sharedSettings by inject<SharedSettings>()

    fun checkLogin() = moveStateTo(state = if (sharedSettings.isLoggedIn) NavigateToMain else Login)

    fun getAuthUrl(): String = authClient.getAuthUrl()

    fun parseDeepLinkAndFetchAccessToken(deepLink: String) {
        deepLink.takeIf { it.startsWith(prefix = "githubmultiplatform://callback") }
            ?.substringAfter(delimiter = "code=", missingDelimiterValue = "")
            ?.takeIf { it.isNotEmpty() }
            ?.let { getAccessToken(code = it) }
    }

    private fun getAccessToken(code: String) {
        coroutineScope.launch {
            val authResponse = authClient.getAccessToken(code = code)
            sharedSettings.updateAccessToken(token = authResponse.accessToken)
            moveStateTo(state = NavigateToMain)
        }
    }

    private fun moveStateTo(state: State) = mutableState.update { state }
}
