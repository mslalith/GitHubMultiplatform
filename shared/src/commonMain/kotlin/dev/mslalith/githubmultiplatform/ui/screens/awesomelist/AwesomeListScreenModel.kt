package dev.mslalith.githubmultiplatform.ui.screens.awesomelist

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.domain.usecase.GetAwesomeListUseCase
import dev.mslalith.githubmultiplatform.ui.screens.awesomelist.AwesomeListScreenState.Loading
import dev.mslalith.githubmultiplatform.ui.screens.awesomelist.AwesomeListScreenState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

internal class AwesomeListScreenModel : SerializableStateScreenModel<AwesomeListScreenState>(initialState = Loading) {

    @delegate:Transient
    private val getAwesomeListUseCase by inject<GetAwesomeListUseCase>()

    fun fetchAwesomeList() {
        coroutineScope.launch {
            val repositories = getAwesomeListUseCase.run().firstOrNull().orEmpty()
            mutableState.update { Success(repositories = repositories) }
        }
    }
}
