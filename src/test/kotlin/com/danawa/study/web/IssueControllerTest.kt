package com.danawa.study.web

import com.danawa.study.config.AuthUser
import com.danawa.study.config.WebConfig
import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import com.danawa.study.model.CommentRequest
import com.danawa.study.model.IssueRequest
import com.danawa.study.service.IssueService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.assertions.print.print
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest(controllers = [IssueController::class])
@Import(HttpEncodingAutoConfiguration::class)
class IssueControllerTest {

    @MockBean
    lateinit var issueService: IssueService

    @Autowired
    lateinit var mockMvc: MockMvc

    val authUser = AuthUser(
        userId = 1,
        username = "황준",
    );


    @Test
    fun create() {
        val issueRequest = IssueRequest(
            "테스트작업",
            "테스트작업중",
            IssueType.TASK,
            IssuePriority.LOW,
            IssueStatus.TODO
        )
        val authUser = AuthUser(
            userId = 1,
            username = "황준",
        );




        val json = jacksonObjectMapper().writeValueAsString(issueRequest)

        mockMvc.perform(post("/api/v1/issues")
            .param("userId", "1")
            .param("username", "황준")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json)
            .characterEncoding("utf-8"))
            .andExpect(status().isOk)
            .andDo(
                MockMvcResultHandlers.print()
            )
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