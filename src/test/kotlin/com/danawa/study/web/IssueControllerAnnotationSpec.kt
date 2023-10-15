package com.danawa.study.web

import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.danawa.study.model.IssueRequest
import com.danawa.study.service.IssueService
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.mockk
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class IssueControllerAnnotationSpec : AnnotationSpec() {

    val issueService = mockk<IssueService>()
    val issueController = IssueController(issueService)
    val mockMvc = MockMvcBuilders.standaloneSetup(issueController).build()

    @Test
    fun `이슈 생성을 호출하면 생성된 이슈 결과가 반환된다`() {
        val request = IssueRequest(
                "황준",
                "테스트작업",
                "테스트작업중",
                IssueType.TASK,
                IssuePriority.LOW,
                IssueStatus.TODO
        )

        val result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/issues")
                        .content(request.toString())
                        .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect {
            status().isNotFound
        }
    }
}