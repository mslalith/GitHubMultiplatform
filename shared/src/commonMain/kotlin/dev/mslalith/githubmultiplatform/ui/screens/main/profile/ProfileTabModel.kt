package dev.mslalith.githubmultiplatform.ui.screens.main.profile

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.profile.ProfileTabUiState
import dev.mslalith.githubmultiplatform.domain.usecase.GetProfileTabInfoUseCase
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Failed
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

internal class ProfileTabModel : SerializableStateScreenModel<CommonState<ProfileTabUiState>>(
    initialState = Loading
) {

    private val getProfileTabInfoUseCase by inject<GetProfileTabInfoUseCase>()

    fun fetchProfile() {
        coroutineScope.launch {
            val profile = getProfileTabInfoUseCase.run().firstOrNull()
            mutableState.update {
                if (profile == null) Failed else Success(value = profile)
            }
        }
    }
}
