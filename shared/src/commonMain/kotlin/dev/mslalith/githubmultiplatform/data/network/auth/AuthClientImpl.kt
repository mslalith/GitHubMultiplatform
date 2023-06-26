package dev.mslalith.githubmultiplatform.data.network.auth

import dev.mslalith.githubmultiplatform.BuildKonfig
import dev.mslalith.githubmultiplatform.data.model.AuthResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class AuthClientImpl(
    private val httpClient: HttpClient
) : AuthClient {

    companion object {
        private const val REDIRECT_URI = "githubmultiplatform://callback"
        private val SCOPES = listOf("repo", "gist", "notifications", "user", "read:user", "project")
    }

    override fun getAuthUrl(): String {
        val scope = SCOPES.joinToString(separator = ",")
        return "https://github.com/login/oauth/authorize?client_id=${BuildKonfig.GITHUB_CLIENT_ID}&redirect_uri=$REDIRECT_URI&scope=$scope"
    }

    override suspend fun getAccessToken(code: String): AuthResponse {
        return httpClient.get(urlString = "https://github.com/login/oauth/access_token") {
            parameter("client_id", BuildKonfig.GITHUB_CLIENT_ID)
            parameter("client_secret", BuildKonfig.GITHUB_CLIENT_SECRET)
            parameter("code", code)
        }.body()
    }
}
