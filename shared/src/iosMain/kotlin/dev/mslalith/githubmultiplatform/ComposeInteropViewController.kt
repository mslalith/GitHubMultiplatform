package dev.mslalith.githubmultiplatform

import androidx.compose.ui.window.ComposeUIViewController
import dev.mslalith.githubmultiplatform.ui.GitHubApp

fun interop() = ComposeUIViewController { GitHubApp() }
