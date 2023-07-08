package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.settings.SharedSettings
import dev.mslalith.githubmultiplatform.domain.usecase.GetProfileTabInfoUseCase
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Failed
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.main.profile.ProfileTabState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

internal class ProfileTabModel : SerializableStateScreenModel<ProfileTabState>(initialState = Loading) {

    private val getProfileTabInfoUseCase by inject<GetProfileTabInfoUseCase>()
    private val sharedSettings by inject<SharedSettings>()

    fun fetchProfile() {
        coroutineScope.launch {
            val profile = getProfileTabInfoUseCase.run().firstOrNull()
            mutableState.update {
                if (profile == null) Failed else Success(profileTabUiState = profile, user = sharedSettings.loggedInUser)
            }
        }
    }
}
