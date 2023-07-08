package dev.mslalith.githubmultiplatform.ui.screens.awesomelist

import dev.mslalith.githubmultiplatform.data.model.AwesomeListRepositories

sealed class AwesomeListScreenState {
    object Loading : AwesomeListScreenState()
    object Failed : AwesomeListScreenState()
    data class Success(
        val repositories: AwesomeListRepositories
    ) : AwesomeListScreenState()
}
