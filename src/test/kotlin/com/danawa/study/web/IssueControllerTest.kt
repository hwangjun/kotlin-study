package com.danawa.study.web

import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.danawa.study.model.IssueRequest
import com.danawa.study.service.IssueService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [IssueController::class])
@Import(HttpEncodingAutoConfiguration::class)
class IssueControllerTest {

    @MockBean
    lateinit var issueService: IssueService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun create() {
        val issueRequest = IssueRequest(
            "황준",
            "테스트작업",
            "테스트작업중",
            IssueType.TASK,
            IssuePriority.LOW,
            IssueStatus.TODO
        )

        val json = jacksonObjectMapper().writeValueAsString(issueRequest)


        val andReturn = mockMvc.perform(
            post("/api/v1/issues")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8")
        )
            .andExpect(status().isOk)
            .andReturn()


    }

    @Test
    fun getAll() {
    }

    @Test
    fun get() {
    }

    @Test
    fun edit() {
    }

    @Test
    fun delete() {
    }
}