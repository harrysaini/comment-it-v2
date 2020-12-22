package com.harrry.comment_it.common.db.models

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,

        @Column(name = "username", nullable = false)
        var username: String,

        @Column(name = "password", nullable = false)
        var password: String,

        @OneToMany(
                mappedBy = "user",
                cascade = [CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH],
                fetch = FetchType.LAZY
        )
        var comments: List<Comment> = emptyList(),

        @Column(name = "created_at", nullable = false)
        var createdAt: Date = Date(),

        @Column(name = "updated_at", nullable = false)
        var updatedAt: Date = Date(),


        ): Serializable