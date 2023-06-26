package dev.mslalith.githubmultiplatform.ui.login

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClient
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginScreenModel : StateScreenModel<LoginScreenModel.State>(initialState = State()), KoinComponent {

    data class State(
        val isLoggedIn: Boolean = false
    )

    private val authClient by inject<AuthClient>()
    private val sharedSettings by inject<SharedSettings>()

    fun getAuthUrl(): String = authClient.getAuthUrl()

    fun getAccessToken(code: String) {
        coroutineScope.launch {
            val authResponse = authClient.getAccessToken(code = code)
            sharedSettings.updateAccessToken(token = authResponse.accessToken)
            mutableState.update { it.copy(isLoggedIn = true) }
        }
    }
}
