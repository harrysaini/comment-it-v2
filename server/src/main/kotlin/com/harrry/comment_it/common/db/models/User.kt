package com.harrry.comment_it.common.db.models

import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 0,

        @Id()
        @Column(name = "username", nullable = false)
        val username: String,

        @Column(name = "password", nullable = false)
        val password: String
)