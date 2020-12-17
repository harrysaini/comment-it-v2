package com.harrry.comment_it.api

import com.harrry.comment_it.api.model.Comment
import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

interface CommentsApi: BaseApi {

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
}