package dev.mslalith.githubmultiplatform.data.settings

import com.russhwolf.settings.Settings
import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedSettings : KoinComponent {

    private val settings: Settings by inject()

    val isLoggedIn: Boolean
        get() = accessToken != null && loggedInUser != null

    val accessToken: String?
        get() = settings.getStringOrNull(key = ACCESS_TOKEN_KEY)

    val loggedInUser: LoggedInUser?
        get() {
            val json = settings.getStringOrNull(key = LOGGED_IN_USER) ?: return null
            return LoggedInUser.fromJson(json = json)
        }

    fun updateAccessToken(token: String) = settings.putString(key = ACCESS_TOKEN_KEY, value = token)
    fun updateLoggedInUser(user: LoggedInUser) = settings.putString(key = LOGGED_IN_USER, value = user.toJson())

    companion object {
        private const val ACCESS_TOKEN_KEY = "access_token_key"
        private const val LOGGED_IN_USER = "logged_in_user"
    }
}

private fun LoggedInUser.toJson(): String = Json.encodeToString(
    serializer = LoggedInUser.serializer(),
    value = this
)

private fun LoggedInUser.Companion.fromJson(json: String): LoggedInUser = Json.decodeFromString(
    deserializer = serializer(),
    string = json
)
