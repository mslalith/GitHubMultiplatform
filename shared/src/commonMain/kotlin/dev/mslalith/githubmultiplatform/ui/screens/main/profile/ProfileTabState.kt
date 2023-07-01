package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import dev.mslalith.githubmultiplatform.data.model.profile.ProfileTabUiState

sealed class ProfileTabState {
    object Loading : ProfileTabState()
    object Failed : ProfileTabState()
    data class Success(
        val profileTabUiState: ProfileTabUiState,
        val user: LoggedInUser?
    ) : ProfileTabState()
}
