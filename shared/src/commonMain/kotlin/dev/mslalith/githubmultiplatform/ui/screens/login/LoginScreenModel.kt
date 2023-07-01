package dev.mslalith.githubmultiplatform.ui.screens.login

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClient
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.Login
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.NavigateToMain
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.Splash
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : StateScreenModel<LoginScreenState>(initialState = Splash), KoinComponent {

    private val authClient by inject<AuthClient>()
    private val sharedSettings by inject<SharedSettings>()

    fun checkLogin() = moveStateTo(state = if (sharedSettings.isLoggedIn) NavigateToMain else Login)

    fun getAuthUrl(): String = authClient.getAuthUrl()

    fun handleDeepLink(deepLink: String) {
        deepLink.takeIf { it.startsWith(prefix = "githubmultiplatform://callback") }
            ?.substringAfter(delimiter = "code=", missingDelimiterValue = "")
            ?.takeIf { it.isNotEmpty() }
            ?.let { handleDeepLinkInternal(code = it) }
    }

    private fun handleDeepLinkInternal(code: String) = coroutineScope.launch {
        val token = getAccessToken(code = code)
        getLoggedInUser(token = token)
        moveStateTo(state = NavigateToMain)
    }

    private suspend fun getAccessToken(code: String): String {
        val authResponse = authClient.getAccessToken(code = code)
        sharedSettings.updateAccessToken(token = authResponse.accessToken)
        return authResponse.accessToken
    }

    private suspend fun getLoggedInUser(token: String): LoggedInUser {
        val loggedInUser = authClient.getUserInfo(token = token)
        sharedSettings.updateLoggedInUser(user = loggedInUser)
        return loggedInUser
    }

    private fun moveStateTo(state: LoginScreenState) = mutableState.update { state }
}
