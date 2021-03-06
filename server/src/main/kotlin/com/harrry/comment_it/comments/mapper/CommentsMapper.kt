package com.harrry.comment_it.comments.mapper

import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.api.model.EditCommentRequest
import com.harrry.comment_it.common.db.models.Comment
import com.harrry.comment_it.common.db.models.User
import com.harrry.comment_it.common.utils.DateFormatter
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommentsMapper {
    fun map(createCommentRequest: CreateCommentRequest, level: Int, user: User): Comment {
        return Comment(
                text = createCommentRequest.text!!,
                level = level,
                user = user
        )
    }

    fun map(comment: Comment, replies: List<com.harrry.comment_it.api.model.Comment> = emptyList()): com.harrry.comment_it.api.model.Comment {
        return com.harrry.comment_it.api.model.Comment(
                id = comment.id,
                text = comment.text,
                replies = replies,
                user = com.harrry.comment_it.api.model.User(
                        comment.user.id,
                        comment.user.username,
                        comment.user.createdAt.toString(),
                        comment.user.updatedAt.toString()
                ),
                level = comment.level,
                createdAt = DateFormatter.formatDate(comment.createdAt),
                updatedAt = DateFormatter.formatDate(comment.updatedAt)
        )
    }

    fun map(comment: Comment, editCommentRequest: EditCommentRequest): Comment {
        comment.text = editCommentRequest.text.toString()
        comment.updatedAt = Date()
        return comment
    }
}