package com.harrry.comment_it.comments.mapper

import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.common.db.models.Comment
import com.harrry.comment_it.common.db.models.User
import org.springframework.stereotype.Component

@Component
class CommentsMapper {
    fun map(createCommentRequest: CreateCommentRequest, level: Int, user: User): Comment {
        return Comment(
                text = createCommentRequest.text!!,
                level = level,
                user = user
        )
    }

    fun map(comment: Comment): com.harrry.comment_it.api.model.Comment {
        return com.harrry.comment_it.api.model.Comment(
                id = comment.id!!,
                text = comment.text,
                replies = comment.replies,
                userId = comment.user.id
        )
    }
}