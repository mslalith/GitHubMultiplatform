package dev.mslalith.githubmultiplatform.ui.main.profile

import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import dev.mslalith.githubmultiplatform.data.model.profile.ProfileTabUiState

sealed class ProfileTabState {
    object Loading : ProfileTabState()
    data class Success(val profileTabUiState: ProfileTabUiState, val user: LoggedInUser?) : ProfileTabState()
    object Failed : ProfileTabState()
}
