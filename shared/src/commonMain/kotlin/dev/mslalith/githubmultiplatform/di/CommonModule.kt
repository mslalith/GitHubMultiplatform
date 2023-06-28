package dev.mslalith.githubmultiplatform.di

import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import dev.mslalith.githubmultiplatform.data.network.GitHubClientImpl
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.domain.usecase.GetProfileTabInfoUseCase
import dev.mslalith.githubmultiplatform.domain.usecase.GetRepositoriesUseCase
import org.koin.dsl.module

internal val commonModule = module {
    single<GitHubClient> { GitHubClientImpl() }
    single { SharedSettings() }

    single { GetRepositoriesUseCase() }
    single { GetProfileTabInfoUseCase() }
}
