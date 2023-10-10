package com.danawa.study.domain.enum

enum class IssueType {

    BUG, TASK;

    companion object {
        operator fun invoke(type:String) = valueOf(type.uppercase())

    }
}