package dev.mslalith.githubmultiplatform.data.network.auth

import dev.mslalith.githubmultiplatform.data.model.AuthResponse

interface AuthClient {
    fun getAuthUrl(): String
    suspend fun getAccessToken(code: String): AuthResponse
}
