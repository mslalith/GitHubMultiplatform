package dev.mslalith.githubmultiplatform.ui.screens.login

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClient
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.Login
import dev.mslalith.githubmultiplatform.ui.screens.login.LoginScreenState.LoginInProgress
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
        val code = deepLink.takeIf { it.startsWith(prefix = "githubmultiplatform://callback") }
            ?.substringAfter(delimiter = "code=", missingDelimiterValue = "")
            ?.takeIf { it.isNotEmpty() }

        if (code == null) moveStateTo(state = Login) else handleDeepLinkInternal(code = code)
    }

    private fun handleDeepLinkInternal(code: String) = coroutineScope.launch {
        moveStateTo(state = LoginInProgress)
        moveStateTo(
            state = when (login(code = code)) {
                is Either.Left -> Login
                is Either.Right -> NavigateToMain
            }
        )
    }

    private suspend fun login(code: String) = either<Unit, Unit> {
        try {
            val token = getAccessToken(code = code)
            getLoggedInUser(token = token)
            Unit.right()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Unit.left()
        }
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
