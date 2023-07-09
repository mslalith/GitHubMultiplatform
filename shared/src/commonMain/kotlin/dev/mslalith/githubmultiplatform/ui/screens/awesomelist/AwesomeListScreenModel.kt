package dev.mslalith.githubmultiplatform.ui.screens.awesomelist

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepositories
import dev.mslalith.githubmultiplatform.domain.usecase.GetAwesomeListUseCase
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class AwesomeListScreenModel : SerializableStateScreenModel<CommonState<AwesomeListRepositories>>(
    initialState = Loading
) {

    @delegate:Transient
    private val getAwesomeListUseCase by inject<GetAwesomeListUseCase>()

    fun fetchAwesomeList() {
        coroutineScope.launch {
            val repositories = getAwesomeListUseCase.run().firstOrNull().orEmpty()
            mutableState.update { Success(value = repositories) }
        }
    }
}
