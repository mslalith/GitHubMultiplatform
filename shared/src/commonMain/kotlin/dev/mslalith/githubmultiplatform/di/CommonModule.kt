package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import dev.mslalith.githubmultiplatform.network.AuthenticationInterceptor
import dev.mslalith.githubmultiplatform.network.GitHubClient
import dev.mslalith.githubmultiplatform.network.GitHubClientImpl
import dev.mslalith.githubmultiplatform.domain.usecase.GetRepositoriesUseCase
import org.koin.dsl.module

internal val commonModule = module {
    single {
        val memoryFirstThenSqlCacheFactory = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)
            .chain(factory = get<SqlNormalizedCacheFactory>())
        ApolloClient.Builder()
            .serverUrl(serverUrl = "https://api.github.com/graphql")
            .addHttpInterceptor(httpInterceptor = AuthenticationInterceptor())
            .normalizedCache(normalizedCacheFactory = memoryFirstThenSqlCacheFactory)
            .build()
    }

    single<GitHubClient> {
        GitHubClientImpl(apolloClient = get())
    }

    single { GetRepositoriesUseCase() }
}
