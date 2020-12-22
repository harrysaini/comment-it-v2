package com.harrry.comment_it.authentication.controller

import com.harrry.comment_it.api.AuthenticationApi
import com.harrry.comment_it.api.model.LoginRequest
import com.harrry.comment_it.api.model.LoginResponse
import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.authentication.service.api.AuthenticationService
import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.exceptions.InvalidRequestDataException
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
        val authenticationService: AuthenticationService,
): AuthenticationApi {
    override fun signupUser(userSignupRequest: UserSignupRequest): ResponseEntity<GenericResponse<User>?> {
        val user = authenticationService.signup(userSignupRequest)
        val response = GenericResponse.success(user)
        return ResponseEntity.ok().body(response)
    }

    override fun loginUser(loginRequest: LoginRequest): ResponseEntity<GenericResponse<LoginResponse>?> {
        val loginResponse = authenticationService.login(loginRequest)
        val response = GenericResponse.success(loginResponse)
        return ResponseEntity.ok().body(response)
    }

    override fun loggedInUser(authentication: Authentication): ResponseEntity<GenericResponse<User>?> {
        val username = authentication.principal as String
        val user  = authenticationService.getUserByUsername(username)
        val response = GenericResponse.success(user)
        return ResponseEntity.ok().body(response)
    }
}