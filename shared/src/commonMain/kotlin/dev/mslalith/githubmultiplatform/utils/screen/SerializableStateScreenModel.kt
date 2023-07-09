package dev.mslalith.githubmultiplatform.utils.screen

import cafe.adriel.voyager.core.model.StateScreenModel
import dev.mslalith.githubmultiplatform.platform.CommonSerializable
import dev.mslalith.githubmultiplatform.ui.state.UiState
import org.koin.core.component.KoinComponent

abstract class SerializableStateScreenModel<S : UiState>(
    initialState: S
) : KoinComponent, CommonSerializable, StateScreenModel<S>(
    initialState = initialState
)
