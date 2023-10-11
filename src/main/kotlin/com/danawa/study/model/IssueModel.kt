package com.danawa.study.model

import com.danawa.study.domain.Comment
import com.danawa.study.domain.Issue
import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class IssueRequest(
    val username : String,
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
)

data class IssueResponse(
    val id: Long,
    val username: String,
    val comments: List<CommentResponse> = emptyList(),
    val summary: String,
    val description: String,
    val type: IssueType,
    val priority: IssuePriority,
    val status: IssueStatus,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime?,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime?,
) {

    companion object {

        operator fun invoke(issue: Issue) =
            with(issue) {
                IssueResponse(
                    id = id!!,
                    username = username,
                    comments = comments.sortedByDescending(Comment::id).map(Comment::toResponse),
                    summary = summary,
                    description = description,
                    type = type,
                    priority = priority,
                    status = status,
                    createdAt = createdAt,
                    updatedAt = updatedAt,
                )
            }

    }

}
