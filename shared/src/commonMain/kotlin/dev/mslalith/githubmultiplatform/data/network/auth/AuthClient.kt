package dev.mslalith.githubmultiplatform.data.network.auth

import dev.mslalith.githubmultiplatform.data.model.AuthResponse
import dev.mslalith.githubmultiplatform.data.model.LoggedInUser

interface AuthClient {
    fun getAuthUrl(): String
    suspend fun getAccessToken(code: String): AuthResponse
    suspend fun getUserInfo(token: String): LoggedInUser
}
