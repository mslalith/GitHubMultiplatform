package dev.mslalith.githubmultiplatform.ui.screens.main.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import compose.icons.Octicons
import compose.icons.octicons.Bell24
import compose.icons.octicons.CommentDiscussion24
import compose.icons.octicons.GitPullRequest24
import compose.icons.octicons.IssueOpened24
import compose.icons.octicons.Skip24
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.notification.Notification
import dev.mslalith.githubmultiplatform.data.model.notification.NotificationType
import dev.mslalith.githubmultiplatform.data.model.notification.Notifications
import dev.mslalith.githubmultiplatform.extensions.timeAgo
import dev.mslalith.githubmultiplatform.ui.bottomsheets.ImageVectorIcon
import dev.mslalith.githubmultiplatform.ui.common.FillSpace
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Green

internal object NotificationsTab : Tab, ScreenTitle {

    override val titleResource: StringResource = SharedRes.strings.notifications

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(resource = SharedRes.strings.notifications)
            val icon = rememberVectorPainter(image = Octicons.Bell24)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { NotificationsTabModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchNotifications() }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when (state) {
                CommonState.Failed -> Text(text = "Failed")
                CommonState.Loading -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }

                is CommonState.Success -> {
                    Column {
                        val notifications = (state as CommonState.Success).value
                        NotificationsList(notifications = notifications)
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationsList(
    notifications: Notifications
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            items = notifications,
            key = { it.id },
        ) {
            NotificationItem(
                notification = it,
                onClick = {}
            )
        }
    }
}

@Composable
private fun NotificationItem(
    notification: Notification,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.Top
    ) {
        HorizontalSpace(space = 24.dp)
        NotificationStateIcon(type = notification.type)

        HorizontalSpace(space = 16.dp)
        Column(
            modifier = Modifier.weight(weight = 1f)
        ) {
            Row {
                Text(
                    text = notification.ownerName,
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp
                )
                Text(
                    text = "/",
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                Text(
                    text = notification.repoName,
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp
                )
                FillSpace()
                Text(
                    text = notification.updatedAt.timeAgo(),
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp
                )
            }
            VerticalSpace(space = 4.dp)
            Text(
                text = notification.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        HorizontalSpace(space = 16.dp)
    }
}

@Composable
private fun NotificationStateIcon(
    type: NotificationType
) {
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    val (icon, color) = remember(key1 = type) {
        when (type) {
            NotificationType.Issue -> Octicons.IssueOpened24 to Bg_Green
            NotificationType.PullRequest -> Octicons.GitPullRequest24 to Bg_Green
            NotificationType.Discussion -> Octicons.CommentDiscussion24 to Bg_Green
            NotificationType.Other -> Octicons.Skip24 to onSurfaceColor
        }
    }

    ImageVectorIcon(
        icon = icon,
        color = color
    )
}
