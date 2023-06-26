package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import dev.mslalith.githubmultiplatform.data.network.AuthenticationInterceptor
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClient
import dev.mslalith.githubmultiplatform.data.network.auth.AuthClientImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

internal val networkModule = module {
    single<ApolloClient> {
        val memoryFirstThenSqlCacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
            .chain(factory = get<SqlNormalizedCacheFactory>())
        ApolloClient.Builder()
            .serverUrl(serverUrl = "https://api.github.com/graphql")
            .addHttpInterceptor(httpInterceptor = AuthenticationInterceptor())
            .normalizedCache(normalizedCacheFactory = memoryFirstThenSqlCacheFactory)
            .build()
    }

    single<HttpClient> {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }

            val timeout = 15_000L
            install(plugin = HttpTimeout) {
                requestTimeoutMillis = timeout
                connectTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
        }
    }

    single<AuthClient> {
        AuthClientImpl(httpClient = get())
    }
}
