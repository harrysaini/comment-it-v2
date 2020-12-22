package com.harrry.comment_it.api

import com.harrry.comment_it.api.model.*
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

interface CommentsApi: BaseApi {

    @GetMapping(
            value = ["/comment"],
            produces = ["application/json"],
    )
    fun getAllCommentsResource(): ResponseEntity<GenericResponse<List<Comment>>>? {
        return this.getAllComments()
    }

    fun getAllComments(): ResponseEntity<GenericResponse<List<Comment>>>?

    @RequestMapping(
            value = ["/comment"],
            produces = ["application/json"],
            method = [RequestMethod.POST]
    )
    fun createCommentResource(
            @Valid @RequestBody createCommentRequest: CreateCommentRequest,
            authentication: Authentication
    ): ResponseEntity<GenericResponse<Comment>?> {
        return this.createComment(createCommentRequest, authentication)
    }

    fun createComment(
            createCommentRequest: CreateCommentRequest,
            authentication: Authentication
    ): ResponseEntity<GenericResponse<Comment>?>




    @RequestMapping(
            value = ["/comment/{commentId}"],
            produces = ["application/json"],
            method = [RequestMethod.GET]
    )
    fun getCommentResource(
            @PathVariable("commentId") commentId: Int,
    ): ResponseEntity<GenericResponse<Comment>?> {
        return this.getComment(commentId)
    }

    fun getComment(
            commentId: Int
    ): ResponseEntity<GenericResponse<Comment>?>


    @RequestMapping(
            value = ["/comment/{commentId}"],
            produces = ["application/json"],
            method = [RequestMethod.PUT]
    )
    fun editCommentResource(
            @Valid @RequestBody editCommentRequest: EditCommentRequest,
            @PathVariable("commentId") commentId: Int,
            authentication: Authentication
    ): ResponseEntity<GenericResponse<Comment>?> {
        return this.editComment(commentId, editCommentRequest, authentication)
    }

    fun editComment(
            commentId: Int,
            editCommentRequest: EditCommentRequest,
            authentication: Authentication
    ): ResponseEntity<GenericResponse<Comment>?>

}