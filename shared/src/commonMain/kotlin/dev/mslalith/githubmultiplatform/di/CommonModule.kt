package dev.mslalith.githubmultiplatform.di

import com.apollographql.apollo3.ApolloClient
import dev.mslalith.githubmultiplatform.network.AuthenticationInterceptor
import dev.mslalith.githubmultiplatform.network.GitHubClient
import dev.mslalith.githubmultiplatform.network.GitHubClientImpl
import dev.mslalith.githubmultiplatform.usecase.GetRepositoriesUseCase
import org.koin.dsl.module

internal val commonModule = module {
    single {
        ApolloClient.Builder()
            .serverUrl(serverUrl = "https://api.github.com/graphql")
            .addHttpInterceptor(AuthenticationInterceptor())
            .build()

    }

    single<GitHubClient> {
        GitHubClientImpl(apolloClient = get())
    }

    single { GetRepositoriesUseCase(githubClient = get()) }
}
