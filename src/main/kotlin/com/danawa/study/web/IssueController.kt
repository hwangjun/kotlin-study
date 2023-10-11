package com.danawa.study.web

import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.model.IssueRequest
import com.danawa.study.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        @RequestBody request: IssueRequest,
    ) = issueService.create(request)

    @GetMapping
    fun getAll(
        @RequestParam(required = false, defaultValue = "TODO") status: IssueStatus,
    ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long,
    ) = issueService.get(id)

    @PutMapping("/{id}")
    fun edit(
        @PathVariable id: Long,
        @RequestBody request: IssueRequest,
    ) = issueService.edit(id, request)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable id: Long,
    ) {
        issueService.delete(id)
    }
}