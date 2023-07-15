package dev.mslalith.githubmultiplatform.ui.screens.main.notifications

import cafe.adriel.voyager.core.model.coroutineScope
import dev.mslalith.githubmultiplatform.data.model.notification.Notifications
import dev.mslalith.githubmultiplatform.data.repository.NotificationsRepo
import dev.mslalith.githubmultiplatform.ui.state.CommonState
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Loading
import dev.mslalith.githubmultiplatform.ui.state.CommonState.Success
import dev.mslalith.githubmultiplatform.utils.screen.SerializableStateScreenModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import kotlin.jvm.Transient

class NotificationsTabModel: SerializableStateScreenModel<CommonState<Notifications>>(
    initialState = Loading
) {

    @delegate:Transient
    private val repo by inject<NotificationsRepo>()

    fun fetchNotifications() {
        coroutineScope.launch {
            mutableState.update { Loading }
            repo.fetch()
            mutableState.update { Success(value = repo.getAll().firstOrNull().orEmpty()) }
        }
    }
}
