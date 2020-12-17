package com.harrry.comment_it.comments.service.api

import com.harrry.comment_it.api.model.Comment
import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.common.db.models.User

interface CommentsService {
    fun createComment(createCommentRequest: CreateCommentRequest, user: User): Comment
}