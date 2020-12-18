package com.harrry.comment_it.common.db.models

import java.io.Serializable
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
        var user: User


): Serializable {

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "parent_comment_id")
        val replies: MutableList<Comment> = mutableListOf()


        fun addReply(comment: Comment) {
                replies.add(comment)
        }
}