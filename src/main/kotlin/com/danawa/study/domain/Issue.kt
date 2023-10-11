package com.danawa.study.domain

import com.danawa.study.domain.enum.IssuePriority
import com.danawa.study.domain.enum.IssueStatus
import com.danawa.study.domain.enum.IssueType
import jakarta.persistence.*

@Entity
@Table
class Issue(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var username : String,

    @OneToMany(fetch = FetchType.EAGER)
    val comments: MutableList<Comment> = mutableListOf(),

    @Column
    var summary: String,

    @Column
    var description: String,

    @Column
    @Enumerated(EnumType.STRING)
    var type: IssueType,

    @Column
    @Enumerated(EnumType.STRING)
    var priority: IssuePriority,

    @Column
    @Enumerated(EnumType.STRING)
    var status: IssueStatus

) : BaseEntity()