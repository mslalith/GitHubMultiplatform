package dev.mslalith.githubmultiplatform.utils.screen

import cafe.adriel.voyager.core.model.StateScreenModel
import dev.mslalith.githubmultiplatform.platform.CommonSerializable
import org.koin.core.component.KoinComponent

abstract class SerializableStateScreenModel<S>(
    initialState: S
) : KoinComponent, CommonSerializable, StateScreenModel<S>(
    initialState = initialState
)
