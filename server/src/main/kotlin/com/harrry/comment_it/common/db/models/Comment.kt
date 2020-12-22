package com.harrry.comment_it.common.db.models

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "comments")
class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int = 0,

        @Column(name = "level")
        var level: Int = 0,

        @Column(name = "text", nullable = false)
        var text: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        var user: User,

        @Column(name = "created_at", nullable = false)
        var createdAt: Date = Date(),

        @Column(name = "updated_at", nullable = false)
        var updatedAt: Date = Date(),

        ): Serializable {

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "parent_comment_id")
        val replies: MutableList<Comment> = mutableListOf()


        fun addReply(comment: Comment) {
                replies.add(comment)
        }
}