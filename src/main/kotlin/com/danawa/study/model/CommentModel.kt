package com.danawa.study.model

import com.danawa.study.domain.Comment

data class CommentRequest(
    val username: String,
    val body: String,
)

data class CommentResponse(
    val id: Long,
    val issueId: Long,
    val body: String,
    val username: String
)

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    issueId = issue.id!!,
    body = body,
    username = username,
)