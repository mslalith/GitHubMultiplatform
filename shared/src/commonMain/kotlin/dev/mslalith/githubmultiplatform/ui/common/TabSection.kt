package dev.mslalith.githubmultiplatform.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TabSection(
    title: StringResource,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Column {
        VerticalSpace(space = 16.dp)
        Header(
            title = title,
            leading = leading,
            trailing = trailing
        )
        VerticalSpace(space = 16.dp)
        content()
    }
}

@Composable
private fun Header(
    title: StringResource,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        if (leading != null) {
            leading.invoke()
            HorizontalSpace(space = 16.dp)
        }
        Text(
            text = stringResource(resource = title),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.weight(weight = 1f)
        )
        if (trailing != null) {
            HorizontalSpace(space = 16.dp)
            trailing.invoke()
        }
    }
}
