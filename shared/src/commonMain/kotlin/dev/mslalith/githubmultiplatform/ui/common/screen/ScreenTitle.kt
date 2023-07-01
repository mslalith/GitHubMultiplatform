package dev.mslalith.githubmultiplatform.ui.common.screen

import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes

interface ScreenTitle {
    val titleResource: StringResource
}

fun Screen.screenTitle(default: StringResource = SharedRes.strings._empty): StringResource = (this as? ScreenTitle)?.titleResource ?: default
fun Tab.screenTitle(default: StringResource = SharedRes.strings._empty): StringResource = (this as? ScreenTitle)?.titleResource ?: default
