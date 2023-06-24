package dev.mslalith.githubmultiplatform.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import dev.mslalith.githubmultiplatform.usecase.GetRepositoriesUseCase
import kotlinx.coroutines.flow.firstOrNull
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val useCase by inject<GetRepositoriesUseCase>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubMultiplatformTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list by produceState(key1 = Unit, initialValue = emptyList()) {
                        val res = useCase().firstOrNull()?.edgesFilterNotNull() ?: return@produceState
                        value = res.mapNotNull { it.node?.name }
                    }
                    GreetingView(list)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingView(items: List<String>) {
    LazyColumn {
        items(items.size) {
            ListItem(
                headlineText = { Text(text = items[it]) }
            )
        }
    }
}
