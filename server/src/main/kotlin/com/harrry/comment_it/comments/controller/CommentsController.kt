package com.harrry.comment_it.comments.controller

import com.harrry.comment_it.api.CommentsApi
import com.harrry.comment_it.api.model.Comment
import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.api.model.EditCommentRequest
import com.harrry.comment_it.comments.service.api.CommentsService
import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.exceptions.InvalidRequestDataException
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RestController

@RestController
class CommentsController(
        val commentsService: CommentsService,
        val userJPARepository: UserJPARepository
): CommentsApi {

    override fun createComment(createCommentRequest: CreateCommentRequest, authentication: Authentication): ResponseEntity<GenericResponse<Comment>?> {
        val username = authentication.principal as String
        val user = userJPARepository.findByUsername(username)
        user?.let {
            val comment = commentsService.createComment(createCommentRequest, user)
            val response = GenericResponse.success(comment)
            return ResponseEntity.ok().body(response)
        } ?: kotlin.run {
            throw InvalidRequestDataException("Invalid username")
        }

    }

    override fun getComment(commentId: Int): ResponseEntity<GenericResponse<Comment>?> {
        val comment = commentsService.getComment(commentId)
        val response = GenericResponse.success(comment)
        return ResponseEntity.ok().body(response)
    }

    override fun editComment(commentId: Int, editCommentRequest: EditCommentRequest, authentication: Authentication): ResponseEntity<GenericResponse<Comment>?> {
        val username = authentication.principal as String
        val user = userJPARepository.findByUsername(username)
        return if(user != null) {
            val comment = commentsService.editComment(commentId, editCommentRequest, user)
            val response = GenericResponse.success(comment)
            return ResponseEntity.ok().body(response)
        } else {
            throw InvalidRequestDataException("Invalid username")
        }
    }

    override fun getAllComments(): ResponseEntity<GenericResponse<List<Comment>>>? {
        val comments: List<Comment> = commentsService.getAllComments()
        val response = GenericResponse.success(comments)
        return ResponseEntity.ok().body(response)
    }
}