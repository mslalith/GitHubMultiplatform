package dev.mslalith.githubmultiplatform.data.model.notification

enum class NotificationType(val type: String) {
    Issue(type = "Issue"),
    PullRequest(type = "PullRequest"),
    Discussion(type = "Discussion"),
    Other(type = "");

    companion object {
        fun fromString(type: String) = NotificationType
            .values()
            .firstOrNull { it.type == type }
            ?: Other
    }
}
