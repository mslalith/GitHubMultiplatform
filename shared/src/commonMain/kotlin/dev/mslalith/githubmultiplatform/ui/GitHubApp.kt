package dev.mslalith.githubmultiplatform.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import dev.mslalith.githubmultiplatform.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.firstOrNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubApp() {
    val useCase = remember { GetRepositoriesUseCase() }
    val items by produceState(key1 = Unit, initialValue = emptyList()) {
        value = useCase.run().firstOrNull()?.repositories.orEmpty().map { it.name }
    }

    LazyColumn {
        items(items.size) {
            ListItem(
                headlineText = { Text(text = items[it]) }
            )
        }
    }
}
