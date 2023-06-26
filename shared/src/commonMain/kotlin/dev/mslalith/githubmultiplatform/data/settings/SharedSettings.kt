package dev.mslalith.githubmultiplatform.data.settings

import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedSettings : KoinComponent {

    private val settings: Settings by inject()

    val isLoggedIn: Boolean = settings.getStringOrNull(key = ACCESS_TOKEN_KEY) != null

    fun updateAccessToken(token: String) = settings.putString(key = ACCESS_TOKEN_KEY, value = token)

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
    }
}