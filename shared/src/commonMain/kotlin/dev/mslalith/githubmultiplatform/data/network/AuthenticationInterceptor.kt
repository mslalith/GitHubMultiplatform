package dev.mslalith.githubmultiplatform.data.network

import com.apollographql.apollo3.api.http.HttpRequest
import com.apollographql.apollo3.api.http.HttpResponse
import com.apollographql.apollo3.network.http.HttpInterceptor
import com.apollographql.apollo3.network.http.HttpInterceptorChain
import dev.mslalith.githubmultiplatform.BuildKonfig

class AuthenticationInterceptor : HttpInterceptor {
    override suspend fun intercept(
        request: HttpRequest,
        chain: HttpInterceptorChain
    ): HttpResponse {
        return chain.proceed(
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer " + BuildKonfig.GITHUB_TOKEN)
                .build()
        )
    }
}
