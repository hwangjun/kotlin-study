package com.danawa.study.web

import com.danawa.study.domain.Issue
import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.danawa.study.model.IssueRequest
import com.danawa.study.model.IssueResponse
import com.danawa.study.service.IssueService
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class IssueControllerDescribeSpec : DescribeSpec({

    describe("IssueController") {

        val issueService = mockk<IssueService>()
        val issueController = IssueController(issueService)

        context("이슈 생성(create)") {

            val request = IssueRequest(
                    "황준",
                    "테스트작업",
                    "테스트작업중",
                    IssueType.TASK,
                    IssuePriority.LOW,
                    IssueStatus.TODO
            )

            val issue = Issue(
                    id = 1L,
                    comments = mutableListOf(),
                    username = request.username,
                    summary = request.summary,
                    description = request.description,
                    type = request.type,
                    priority = request.priority,
                    status = IssueStatus.TODO
            )

            val issueResponse = IssueResponse(issue);

            every { issueService.create(request) } returns issueResponse

            it("생성된 이슈가 반환된다") {
                val response = issueController.create(request)
                response shouldBe issueResponse
            }
        }
    }
})