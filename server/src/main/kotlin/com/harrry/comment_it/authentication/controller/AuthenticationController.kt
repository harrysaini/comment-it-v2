package com.harrry.comment_it.authentication.controller

import com.harrry.comment_it.api.AuthenticationApi
import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.authentication.service.api.AuthenticationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
        val authenticationService: AuthenticationService
): AuthenticationApi {
    override fun signupUser(userSignupRequest: UserSignupRequest): ResponseEntity<User?> {
        val user = authenticationService.signup(userSignupRequest)
        return ResponseEntity.ok().body(user)
    }
}