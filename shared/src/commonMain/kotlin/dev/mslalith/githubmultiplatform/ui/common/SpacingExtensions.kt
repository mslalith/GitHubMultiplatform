package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun HorizontalSpace(space: Dp) = Spacer(modifier = Modifier.width(width = space))

@Composable
fun VerticalSpace(space: Dp) = Spacer(modifier = Modifier.height(height = space))
