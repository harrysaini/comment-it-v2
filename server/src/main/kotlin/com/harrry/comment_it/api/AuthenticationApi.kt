package com.harrry.comment_it.api

import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


interface AuthenticationApi : BaseApi {

    @RequestMapping(
            value = ["/auth/signup"],
            produces = ["application/json"],
            method = [RequestMethod.POST]
    )
    fun signupUserResource(
            @RequestBody userSignupRequest: UserSignupRequest
    ): ResponseEntity<User?> {
        return this.signupUser(userSignupRequest)
    }

    fun signupUser(
            userSignupRequest: UserSignupRequest
    ): ResponseEntity<User?>
}
