package dev.mslalith.githubmultiplatform.ui.login

sealed class LoginScreenState {
    object Splash : LoginScreenState()
    object Login : LoginScreenState()
    object NavigateToMain : LoginScreenState()
}
