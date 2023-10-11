package com.danawa.study.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndUsername(id : Long, username: String) : Comment?
}