package dev.mslalith.githubmultiplatform.domain.usecase

import dev.mslalith.githubmultiplatform.GetProfileQuery
import dev.mslalith.githubmultiplatform.data.model.profile.ProfileTabUiState
import dev.mslalith.githubmultiplatform.data.network.GitHubClient
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.domain.dto.toProfilePinnedRepositories
import dev.mslalith.githubmultiplatform.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import org.koin.core.component.inject

class GetProfileTabInfoUseCase : FlowUseCase.NoParams<ProfileTabUiState>() {

    private val githubClient: GitHubClient by inject()
    private val sharedSettings by inject<SharedSettings>()

    private val login = sharedSettings.loggedInUser?.login ?: error(message = "User not logged in")

    override fun run(): Flow<ProfileTabUiState> = githubClient
        .getProfileTabInfo(login = login)
        .mapNotNull { it.toProfileTabState() }

    private fun GetProfileQuery.Data.toProfileTabState(): ProfileTabUiState? {
        user ?: return null
        return ProfileTabUiState(
            name = user.name ?: login,
            login = login,
            avatarUrl = user.avatarUrl.toString(),
            pronouns = user.pronouns,
            email = user.email,
            followersCount = user.followers.totalCount,
            followingCount = user.following.totalCount,
            pinnedRepositories = user.pinnableItems.toProfilePinnedRepositories(),
            repositoriesCount = user.repositories.totalCount,
            starredRepositoriesCount = user.starredRepositories.totalCount
        )
    }
}
