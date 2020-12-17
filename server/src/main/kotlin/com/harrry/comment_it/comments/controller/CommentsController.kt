package com.harrry.comment_it.comments.controller

import com.harrry.comment_it.api.CommentsApi
import com.harrry.comment_it.api.model.Comment
import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.comments.service.api.CommentsService
import com.harrry.comment_it.common.db.models.User
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentsController(
        val commentsService: CommentsService
): CommentsApi {
    override fun createComment(createCommentRequest: CreateCommentRequest, authentication: Authentication): ResponseEntity<GenericResponse<Comment>?> {
        val user = authentication.principal as User
        val comment = commentsService.createComment(createCommentRequest, user)
        val response = GenericResponse.success(comment)
        return ResponseEntity.ok().body(response)
    }
}