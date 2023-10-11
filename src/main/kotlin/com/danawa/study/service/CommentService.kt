package com.danawa.study.service

import com.danawa.study.domain.Comment
import com.danawa.study.domain.CommentRepository
import com.danawa.study.domain.IssueRepository
import com.danawa.study.model.CommentRequest
import com.danawa.study.model.CommentResponse
import com.danawa.study.exception.NotFoundException
import com.danawa.study.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(issueId: Long, request: CommentRequest) : CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        val comment = Comment(
            issue = issue,
            username = request.username,
            body = request.body,
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(id: Long, request: CommentRequest): CommentResponse? {
        return commentRepository.findByIdAndUsername(id, request.username)?.run {
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, request: CommentRequest) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")
        commentRepository.findByIdAndUsername(id, request.username)?.let { comment->
            issue.comments.remove(comment)
        }
    }

}