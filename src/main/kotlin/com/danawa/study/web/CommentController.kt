package com.danawa.study.web

import com.danawa.study.model.CommentRequest
import com.danawa.study.model.CommentResponse
import com.danawa.study.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController(
    private val commentService: CommentService,
) {

    @PostMapping
    fun create(
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ): CommentResponse {
        return commentService.create(issueId, request)
    }

    @PutMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @RequestBody request: CommentRequest,
    ) = commentService.edit(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable issueId: Long,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest,
    ) {
        commentService.delete(issueId, id, request)
    }

}