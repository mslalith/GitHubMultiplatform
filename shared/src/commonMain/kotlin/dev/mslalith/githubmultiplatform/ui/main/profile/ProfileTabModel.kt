package dev.mslalith.githubmultiplatform.ui.main.profile

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.LoggedInUser
import dev.mslalith.githubmultiplatform.data.model.profile.ProfileTabState
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class ProfileTabModel : StateScreenModel<ProfileTabModel.State>(initialState = State.Loading), KoinComponent {
    sealed class State {
        object Loading : State()
        data class Success(val profileTabState: ProfileTabState, val user: LoggedInUser?) : State()
        object Failed : State()
    }

    private val getProfileUseCase by inject<GetProfileUseCase>()
    private val sharedSettings by inject<SharedSettings>()

    fun fetchProfile() {
        coroutineScope.launch {
            val profile = getProfileUseCase.run().firstOrNull()
            mutableState.update {
                if (profile == null) State.Failed else State.Success(profileTabState = profile, user = sharedSettings.loggedInUser)
            }
        }
    }
}
