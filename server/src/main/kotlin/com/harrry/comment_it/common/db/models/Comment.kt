package com.harrry.comment_it.common.db.models

import javax.persistence.*

@Entity
@Table(name = "comments")
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Int,

        @Column(name = "level")
        val level: Int = 0,

        @Column(name = "text", nullable = false)
        val text: String,

        @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "userId")
        val user: User,

        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        @JoinColumn(name = "replyId")
        val replies: List<Comment>
)