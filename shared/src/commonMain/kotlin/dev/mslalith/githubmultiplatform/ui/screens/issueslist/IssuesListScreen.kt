package dev.mslalith.githubmultiplatform.ui.screens.issueslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import compose.icons.Octicons
import compose.icons.octicons.CheckCircle24
import compose.icons.octicons.Comment24
import compose.icons.octicons.IssueOpened24
import compose.icons.octicons.IssueReopened24
import compose.icons.octicons.Search24
import compose.icons.octicons.Skip24
import dev.icerock.moko.resources.StringResource
import dev.mslalith.githubmultiplatform.SharedRes
import dev.mslalith.githubmultiplatform.data.model.Issue
import dev.mslalith.githubmultiplatform.type.IssueState
import dev.mslalith.githubmultiplatform.type.IssueStateReason
import dev.mslalith.githubmultiplatform.ui.common.HorizontalLine
import dev.mslalith.githubmultiplatform.ui.common.HorizontalSpace
import dev.mslalith.githubmultiplatform.ui.common.RoundIcon
import dev.mslalith.githubmultiplatform.ui.common.VerticalSpace
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenActions
import dev.mslalith.githubmultiplatform.ui.common.screen.ScreenTitle
import dev.mslalith.githubmultiplatform.ui.common.topbar.ScreenAwareTopBar
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_400
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Gray_Dark_800
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Green
import dev.mslalith.githubmultiplatform.ui.theme.Bg_Purple
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class IssuesListScreen : Screen, ScreenTitle, ScreenActions {

    override val titleResource: StringResource = SharedRes.strings.issues

    @Composable
    override fun RowScope.Actions() {
        RoundIcon(
            icon = Octicons.Search24,
            onClick = {}
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { IssuesListScreenModel() }
        val state by screenModel.state.collectAsState()

        LaunchedEffect(key1 = Unit) { screenModel.fetchIssues() }

        Scaffold(
            topBar = { ScreenAwareTopBar() }
        ) {
            Box(
                modifier = Modifier.padding(paddingValues = it)
            ) {
                when (state) {
                    IssuesListScreenState.Failed -> Text(text = "Failed")
                    IssuesListScreenState.Loading -> Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }

                    is IssuesListScreenState.Success -> {
                        Column {
                            val issues = (state as IssuesListScreenState.Success).issues
                            IssuesList(issues = issues)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun IssuesList(
    issues: List<Issue>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        items(
            count = issues.size,
            key = { issues[it].id },
        ) { index ->
            IssueItem(
                issue = issues[index],
                onClick = {}
            )
            if (index != issues.lastIndex) {
                HorizontalLine(height = 1.dp)
            }
        }
    }
}

@Composable
private fun IssueItem(
    issue: Issue,
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
        IssueStateIcon(
            state = issue.state,
            stateReason = issue.stateReason
        )
        HorizontalSpace(space = 16.dp)
        Column {
            Row {
                Text(
                    text = issue.repoAuthor,
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
                    text = issue.repoName,
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp
                )
                HorizontalSpace(space = 4.dp)
                Text(
                    text = "#${issue.number}",
                    color = Bg_Gray_Dark_400,
                    fontSize = 14.sp
                )
            }
            VerticalSpace(space = 4.dp)
            Text(
                text = issue.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            VerticalSpace(space = 8.dp)
            OwnerInfoAndComments(
                assigneeAvatarUrl = issue.assigneeAvatarUrl,
                totalComments = issue.totalComments
            )
        }
        HorizontalSpace(space = 24.dp)
    }
}

@Composable
private fun IssueStateIcon(
    state: IssueState,
    stateReason: IssueStateReason?
) {
    val onSurfaceColor = MaterialTheme.colorScheme.onSurface

    val (icon, color) = remember(key1 = state, key2 = stateReason) {
        when (state) {
            IssueState.UNKNOWN__ -> Octicons.Skip24 to onSurfaceColor
            IssueState.OPEN -> Octicons.IssueOpened24 to Bg_Green
            IssueState.CLOSED -> when (stateReason) {
                null, IssueStateReason.COMPLETED -> Octicons.CheckCircle24 to Bg_Purple
                IssueStateReason.NOT_PLANNED -> Octicons.Skip24 to onSurfaceColor
                IssueStateReason.REOPENED -> Octicons.IssueReopened24 to Bg_Green
                IssueStateReason.UNKNOWN__ -> Octicons.Skip24 to onSurfaceColor
            }
        }
    }

    Image(
        painter = rememberVectorPainter(image = icon),
        contentDescription = "",
        colorFilter = ColorFilter.tint(color = color)
    )
}

@Composable
private fun OwnerInfoAndComments(
    assigneeAvatarUrl: String?,
    totalComments: Int
) {
    Row {
        if (assigneeAvatarUrl != null) {
            KamelImage(
                resource = asyncPainterResource(data = assigneeAvatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(size = 22.dp)
                    .clip(shape = CircleShape)
            )
        }
        if (assigneeAvatarUrl != null && totalComments >= 0) {
            HorizontalSpace(space = 8.dp)
        }
        if (totalComments > 0) {
            Comments(count = totalComments)
        }
    }
}

@Composable
private fun Comments(
    count: Int
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 36.dp))
            .background(color = Bg_Gray_Dark_800)
            .height(height = 24.dp)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberVectorPainter(image = Octicons.Comment24),
            contentDescription = "",
            colorFilter = ColorFilter.tint(color = Bg_Gray_Dark_400),
            modifier = Modifier.size(size = 14.dp)
        )
        HorizontalSpace(space = 6.dp)
        Text(
            text = count.toString(),
            color = Bg_Gray_Dark_400,
            fontSize = 14.sp
        )
    }
}