package com.danawa.study.web

import com.danawa.study.domain.Issue
import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.danawa.study.model.IssueRequest
import com.danawa.study.model.IssueResponse
import com.danawa.study.service.IssueService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class IssueControllerBehaviorSpec : BehaviorSpec({

    given("IssueController") {

        val issueService = mockk<IssueService>()
        val issueController = IssueController(issueService)
        val mockMvc = MockMvcBuilders.standaloneSetup(issueController).build()

        `when`("이슈 생성을 호출한다면") {

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

            val result = mockMvc.perform(
                    MockMvcRequestBuilders.post("/api/v1/issues")
                            .content(request.toString())
                            .contentType(MediaType.APPLICATION_JSON)
            )

            every { issueService.create(request) } returns issueResponse

            then("응답으로 생성된 이슈가 반환된다") {
                val response = issueController.create(request)

                response shouldBe issueResponse
            }

            then("HTTP 응답코든느 200이 반환된다") {
                result.andExpect {
                    status().isOk
                }
            }
        }
    }
})