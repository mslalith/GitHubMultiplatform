package dev.mslalith.githubmultiplatform.ui.screens.login

sealed class LoginScreenState {
    object Splash : LoginScreenState()
    object Login : LoginScreenState()
    object LoginInProgress : LoginScreenState()
    object NavigateToMain : LoginScreenState()
}
